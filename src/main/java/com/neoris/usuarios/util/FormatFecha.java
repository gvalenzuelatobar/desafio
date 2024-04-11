package com.neoris.usuarios.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatFecha {
	
	private static final String DATEHOUR_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	public static String convertirfechaToString(Date fecha) {
				
		SimpleDateFormat simpleformat = new SimpleDateFormat(DATEHOUR_FORMAT);		
		return simpleformat.format(fecha);
	}

}
