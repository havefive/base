package com.lzc.company.utils;

import java.util.Random;

import android.graphics.Color;

public class GetPhone {

	public static int randomColor() {
		Random random = new Random();
		return Color.rgb(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
	}

}
