package br.edu.ifpb.mt.ads.dac.util.date;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public final static int DIA = 5;

	public final static int MES = 2;

	public static Date somar(Date date, int quantidade, int tipo) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(tipo, +quantidade);

		Date nova = new Date(calendar.getTimeInMillis());

		return nova;
	}

	public static Date subtrair(Date date, int quantidade, int tipo) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(tipo, -quantidade);

		Date nova = new Date(calendar.getTimeInMillis());

		return nova;
	}
}
