package com.ride.main;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.ride.dao.ViajeFacade;
import com.ride.entity.Viaje;

public class TerminarViaje {

	private Viaje v;
	private ViajeFacade vf;

	
	
	public TerminarViaje() {
		vf = new ViajeFacade();
		v = new Viaje();
	}


	public void run(String hora_actualS) {
		List<Viaje> lista = new ArrayList<Viaje>();
		lista = vf.mostrar();
		
		Time hora_actual = Time.valueOf(hora_actualS);
		
		if (lista != null) {
			for (Viaje obj : lista) {
				long resultado = (((obj.getHoraInicio().getTime() - hora_actual.getTime()) / (60 * 1000)) * (-1));
				if (resultado > 5) {
					v.setIdViaje(obj.getIdViaje());
					v.setEstado(5);
					v.setHoraInicio(obj.getHoraInicio());
					vf.update(v);
				}
			}
			/* Gson gson = new Gson(); String data = gson.toJson(lista); JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray(); String listas = gson.toJson(jsonArray); */
		} else {
			
		}
	}
}
