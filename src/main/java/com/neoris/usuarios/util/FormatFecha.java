package com.neoris.usuarios.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatFecha {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static String convertirfechaToString(Date fecha) {
				
		
		try {
			return sdf.format(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
