package com.ride.main;


import java.util.Timer;
import java.util.TimerTask;


public class main {

	public static void main(String[] args) {
		final Timer timer = new Timer();

		TerminarViaje bot1 = new TerminarViaje();

		TimerTask task = new TimerTask() { 

			@Override
			public void run() {
				bot1.run("10:50:00");
				System.out.println("Se ejecuta solicitud");
				
			}
		};

		timer.schedule(task, 0, 3000);

	}

}