package packModelo;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;

public class OrdenadorD extends Modo {

	public OrdenadorD() {
		super("Ordenador modo Dificil");
	}

	/**
	 * Simula un turno a partir de la posición introducida por el jugador.
	 *
	 * @param pColumna la columna en la que se va a introducir la ficha en la jugada
	 * @return JSON {haGanadoA:boolean, haGanadoB:boolean,
	 * posicionesGanadoras {x1,y1;x2,y2:;...},lleno: boolean} / null
	 * @author Alvaro Dueñas
	 */
	public JSONObject jugar(int pColumna) {
		Tablero tablero = Tablero.getmTablero();
		JSONObject json1 = tablero.introducirFicha(pColumna, true);
		if (json1 == null) {
			return null;
		}
		JSONObject res = new JSONObject();
		res.put("lleno", tablero.tableroLleno());
		res.put("posicionesGanadoras", null);
		res.put("haGanadoA", false);
		res.put("haGanadoB", false);
		JSONArray json2 = tablero.haGanado(Integer.parseInt(String.valueOf(json1.get("fila"))),
				Integer.parseInt(String.valueOf(json1.get("columna"))), true);
		if (json2 == null) {
			if (!tablero.tableroLleno()) {
				JSONObject posIn2;
				Pair<Integer, Integer> optB = tablero.getOptimo(false);
				if (optB.getValue() == 3) {
					posIn2 = tablero.introducirFicha(optB.getKey(), false);
				} else {
					Pair<Integer, Integer> optA = tablero.getOptimo(true);
					if (optA.getValue() > optB.getValue()) {
						posIn2 = tablero.introducirFicha(optA.getKey(), false);
					} else if (optB.getValue() > optA.getValue()) {
						posIn2 = tablero.introducirFicha(optB.getKey(), false);
					} else {
						if ((new Random()).nextBoolean()) {
							posIn2 = tablero.introducirFicha(optA.getKey(), false);
						} else {
							posIn2 = tablero.introducirFicha(optB.getKey(), false);
						}
					}
				}
				JSONArray json3 = tablero.haGanado(Integer.parseInt(String.valueOf(posIn2.get("fila"))),
						Integer.parseInt(String.valueOf(posIn2.get("columna"))), false);
				if (json3 == null) {
					res.replace("lleno", tablero.tableroLleno());
				} else {
					res.put("haGanadoB", true);
					res.replace("posicionesGanadoras", json3);
				}
			} else {
				res.replace("lleno", true);
			}
		} else {
			res.put("haGanadoA", true);
			res.replace("posicionesGanadoras", json2);
		}
		return res;
	}
}