package com.denjom;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonMapper {
	
	private static byte[] jsonData;
	public static final String ROOT_NODE = "root";
	private static final AtomicLong counter = new AtomicLong();
	
	static {
		InputStream is = JsonMapper.class.getClassLoader().getResourceAsStream("navigation.json");
		try {
			jsonData = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Navigation loadAndParseNavigationJson() {
		System.out.println("Times hit: " + counter.incrementAndGet());
		Navigation nav = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			nav = objectMapper.readValue(jsonData, Navigation.class);
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return nav;
	}
	
	private static Navigation getRootNode(Navigation nav) {
		Navigation nav1 = new Navigation();
		nav1.setId(nav.getId());
		nav1.setName(nav.getName());
		nav1.setUrl(nav.getUrl());
		for (Navigation n : nav.getChildren()) {
			Navigation nav2 = new Navigation();
			nav2.setId(n.getId());
			nav2.setName(n.getName());
			nav2.setUrl(n.getUrl());
			nav1.getChildren().add(nav2);
		}
		return nav1;
	}
	
	public static String getNodeById(String id, Navigation nav) {
		ObjectMapper objectMapper = new ObjectMapper();
		StringWriter stringNav = new StringWriter();
		try {
			if (ROOT_NODE.equals(id)) {
				objectMapper.writeValue(stringNav, getRootNode(nav));
				return stringNav.toString();
			} else {
				if (id.equals(nav.getId())) {
					objectMapper.writeValue(stringNav, nav);
					return stringNav.toString();
				} else {
					for (Navigation n : nav.getChildren()) {
						if (id.equals(n.getId())) {
							Navigation nav1 = new Navigation();
							nav1.setId(n.getId());
							nav1.setName(n.getName());
							nav1.setUrl(n.getUrl());
							objectMapper.writeValue(stringNav, nav1);
							return stringNav.toString();
						} else {
							String str = getNodeById(id, n);
							if (str != null) {
								return str;
							} 
						}
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				stringNav.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null; 
	}
}