package tp2.server;

import javax.xml.ws.Endpoint;

import tp2.server.service.ConcreteHotelService;

public class HotelServicePublisher
{	
	private static final String SERVICE_URL = "http://localhost:3000/HotelService";

	public static void main(String[] args)
	{
		Endpoint.publish(SERVICE_URL, new ConcreteHotelService());
		System.out.println("Server is ready");
	}
}
