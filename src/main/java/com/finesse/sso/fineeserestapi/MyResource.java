package com.finesse.sso.fineeserestapi;

import java.net.HttpURLConnection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import com.cisco.ccbu.common.ids.client.IdSClient;
import com.cisco.ccbu.common.ids.client.IdSClientException;
import com.cisco.ccbu.common.ids.client.factory.IdSClientFactory;
import com.cisco.ccbu.common.ids.client.impl.IdSClientConfigurationImpl;
import com.cisco.ccbu.common.ids.client.models.AccessToken;
import com.google.gson.Gson;
import com.sun.research.ws.wadl.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    
    @POST
	@Path("/authcode")
    @Consumes(MediaType.APPLICATION_JSON)
    public String data(String m)throws java.io.IOException,IdSClientException  {
    	
    	final ResourceConfig resourceConfig = new ResourceConfig();
    	resourceConfig.register(new CORSFilter());
//    	

		IdSClientConfigurationImpl config = new IdSClientConfigurationImpl("/idsclient.conf");

    	POJO model=new Gson().fromJson(m,POJO.class);
    	System.out.println(m);
    	System.out.println(model.getCode());
    	System.out.println(model.getUrl());
    	String authcode=model.getCode();
		
		try {
			IdSClient client = IdSClientFactory.getIdSClient();
			System.out.println(client);

			AccessToken token = client.getAccessToken(authcode, model.getUrl());
			System.out.println(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        return "Worked";
    }
}
