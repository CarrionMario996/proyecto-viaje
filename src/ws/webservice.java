package ws;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.ride.dao.ViajeFacade;
import com.ride.entity.Viaje;

@Path("/WSViaje")
public class webservice {

	private Viaje v;
	private ViajeFacade vf;

	public webservice() {
		vf = new ViajeFacade();
		v = new Viaje();
	}


	@POST
	@Path("/listaViajexml")
	// @Produces(MediaType.APPLICATION_JSON)
	public String viajesAll(@QueryParam(value = "hora_actual") Time hora_actual) {
		List<Viaje> lista = new ArrayList<Viaje>();
		lista = vf.mostrar();

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
			return "Se modifico";
		} else {
			return "No hay datos en la tabla";
		}
	}

}
