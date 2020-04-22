package com.javatechie.jwt.api.util;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
@Component

public class S3util {
    @Autowired
	public  AmazonS3 s3client;
	
  public AmazonS3 createClientconnection(String accesskey,String secretkey) {
	   
	  AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);
	  s3client = AmazonS3ClientBuilder
			  .standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(Regions.US_EAST_2)
			  .build();
	  return s3client;
	  
  }
  public boolean createBucket(String bucketName) {
	  
	   
	  
		/*
		 * if(s3client.doesBucketExist(bucketName)) {
		 * System.out.println("Bucket name is not available." +
		 * " Try again with a different Bucket name."); return true; }
		 */
	  s3client.doesBucketExist(bucketName);
	  s3client.createBucket(bucketName);
	  return false;
	  
  }
  
  public List<Bucket> getlistofBuckets() {
	  List<Bucket> buckets = s3client.listBuckets();
	  for(Bucket bucket : buckets) {
	      System.out.println(bucket.getName());
	  }
	  return buckets;
  }
  
	public void deleteBucket() {
		
		try {
		    s3client.deleteBucket("baeldung-bucket-test2");
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    return;
		}
		
	}
	
	public void getListofobjects(String bucketName) {
		
		ObjectListing objectListing = s3client.listObjects(bucketName);
		for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
		    System.out.println(os.getKey());
		}
		
	}
	
	public void downloadObect(String bucketName, String filepath,AmazonS3 s3Client) throws IOException {
		
		/* S3Object s3object = s3client.getObject(bucketName, "picture/pic.png"); */
		S3Object s3object = s3client.getObject(bucketName, filepath);
		
		S3ObjectInputStream reader = s3object.getObjectContent();
		//FileUtils.copyInputStreamToFile(inputStream, new File("/Users/user/Desktop/hello.txt"));
		//InputStream reader = new BufferedInputStream( object.getObjectContent());
				File file = new File(filepath);      
				OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));

				int read = -1;

				while ( ( read = reader.read() ) != -1 ) {
				    writer.write(read);
				}

				writer.flush();
				writer.close();
				reader.close();
		 
		
	}
	
	public void DeleteSingleObject(String bucketname, String filepath) {
		
		//s3client.deleteObject("baeldung-bucket","picture/pic.png");
		
		s3client.deleteObject(bucketname, filepath);
	}
	
	public void DeleteMultipleObects(String objKeyArr[]) {
		
		/*
		 * String objkeyArr[] = { "document/hello.txt", "document/pic.png" };
		 */
				 
				DeleteObjectsRequest delObjReq = new DeleteObjectsRequest("baeldung-bucket")
				  .withKeys(objKeyArr);
				s3client.deleteObjects(delObjReq);
		
		
	}
	
	public String generateDocumentUrl(String bucketName, String filepath, AmazonS3 s3Client) {
		 
		// Set the presigned URL to expire after one hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
		System.out.println("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, filepath)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
     // Generate the presigned URL.
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        

        System.out.println("Pre-Signed URL: " + url.toString());
	
		
		return url.toString();
		
	}
	
	
	
	
	public void uploadSingleDocument(String bucketName,String fileObjKeyName,String fileName) {
		 Regions clientRegion = Regions.US_EAST_2;
	       // String bucketName = "ahmad457";
	        //String stringObjKeyName = "profile_pics/";
	        //String fileObjKeyName = "profile_pics/".concat("image002.jpg");
	        //String fileName = "/home/ahmad/Desktop/image002.jpg";

	        try {
	            //This code expects that you have AWS credentials set up per:
	            // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
	            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
	                    .withRegion(clientRegion)
	                    .build();

	            // Upload a text string as a new object.
	           // s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

	            // Upload a file as a new object with ContentType and title specified.
	            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
	           // ObjectMetadata metadata = new ObjectMetadata();
	            //metadata.setContentType("plain/text");
	            //metadata.addUserMetadata("x-amz-meta-title", "someTitle");
	            //request.setMetadata(metadata);
	            s3Client.putObject(request);
	        } catch (AmazonServiceException e) {
	            // The call was transmitted successfully, but Amazon S3 couldn't process 
	            // it, so it returned an error response.
	            e.printStackTrace();
	        } catch (SdkClientException e) {
	            // Amazon S3 couldn't be contacted for a response, or the client
	            // couldn't parse the response from Amazon S3.
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
	
	
	
	
	


