package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Se encarga de toda la lógica del juego, almacena una matriz con todas las posiciones del tablero y en cada posición una ficha.
 */

public class Tablero {

	/**
	 * La matriz representa el tablero, es de tipo booleano de modo que true será rojo, false azul y null que la posición está vacía
	 */
	private boolean[][] matriz;
	/**
	 * Atributo estático para el patrón singleton.
	 */
	private static Tablero mTablero;

	private Tablero() {

	}

	public static Tablero getmTablero() {
		if (mTablero == null) {
			mTablero = new Tablero();
		}
		return mTablero;
	}

	/**Pre: recibe como parámetro una columna y un jugador
	 * Post: introduce la ficha en la columna indicada en caso de que sea posible. Devuelve un json con la siguiente
	 * forma: JSON{x:int,y:int, lleno:boolean} en caso de que la ficha se pueda introducir. Un JSON nulo
	 *  en caso de que la ficha no se pueda introducir.
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha
	 * @param pJugador el color de la ficha
	 */
	public JSONObject introducirFicha(int pColumna, boolean pJugador) {
		boolean ocupada=true;
		JSONObject json=new JSONObject();
		if(!ocupada(pColumna)){
			json=anadirFicha(pColumna,pJugador);
			ocupada=false;
		}
		boolean lleno=tableroLleno();


		if(ocupada){
			return null;
		}
		else{
			json.put("lleno",lleno);

			return json;
		}



	}
	/**Pre: recibe como parámetro una columna y el indicador de un jugador. Es posible introducir la ficha
	 * Post: introduce la ficha en la columna indicada en caso de que sea posible. Devuelve un json con la siguiente
	 * forma: JSON{x:int,y:int}
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha
	 * @param pJugador el color de la ficha
	 */
	private JSONObject anadirFicha(int pColumna, boolean pJugador) {
		int i=0;
		Boolean pos=matriz[i][pColumna];
		while(pos!=null){
			i++;
			pos=matriz[i][pColumna];

		}
		matriz[i][pColumna]=pJugador;
		JSONObject json=new JSONObject();
		json.put("x",i);
		json.put("y",pColumna);
		return json;
	}

	/* Precondición: recibe como parámetro una columna
	 *Postcondición: devuelve un booleano indicando si la columna está llena o no
	 * @author Nuria Lebeña
	 * @param pColumna entero que hace referencia a la columna
	 */
	public boolean ocupada(int pColumna) {
		int i=0;
		boolean ocupada=true;
		while(i<matriz.length && ocupada){
			Boolean pos=matriz[i][pColumna];
			if(pos==null){
				ocupada=false;
			}
			i=i+1;

		}
		return ocupada;
	}

	/**Precondición:recibe como parámetro las coordenadas de la última ficha introducida y su color
	 * PostCondicion: si el jugador ha ganado devuelve las posiciones en las que están las fichas que forman 4 en raya
	 * @param pX: posición x de la última ficha introducida
	 * @param pY: posición y de la última ficha introducida
	 * @param pColor: color de la ficha
	 * @return JSONObject de la forma ..... si el jugador ha ganado, null si no ha ganado
	 */
	public JSONObject haGanado(int pX, int pY,boolean pColor) {
		int colindantes=getColindantes(pX,pY,pColor);
		boolean ganado=false;
		JSONObject json=new JSONObject();
		if (colindantes==3){
			ganado=true;
			json=getCoordenadasGanadoras(pX,pY,pColor);
		}
		return json;
	}

	/**
	 * Comprueba si el color de la posición dada es igual a pColor
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public boolean esColor(int pX, int pY, boolean pColor) {
		return matriz[pX][pY] == pColor;
	}

	/**
	 * Comprueba si los valores recibidos  están dentro del tablero
	 * @param pX
	 * @param pY
	 */
	public boolean posValida(int pX, int pY) {
		boolean valida = false;
		if (pX >= 0 && pX < matriz.length && pY >= 0 && pY < matriz.length){
			valida = true;
		}
		return valida;
	}
	/**
	 * Post:El método se encarga de inicializar todas las posiciones del tablero a null
	 * @author Nuria Lebeña
	 */
	public void inicializarTablero() {
		matriz =new Boolean[6][9];

		for (int x=0; x<matriz.length;x++){
			for(int y=0;y<matriz[0].length;y++){
				matriz[x][y]= null;
			}
		}
	}

	public void imprimirTablero(){
		System.out.println("\n");
		for (int x=matriz.length-1; x>=0;x--){
			for(int y=0;y<matriz[0].length;y++){
				Boolean contenido=matriz[x][y];
				if (contenido==null|| contenido){
					System.out.print(contenido+"   ");
				}
				else{
					System.out.print(contenido+"  ");
				}


			}
			System.out.println("\n");
		}
	}
	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param combinacion
	 * @param pCuantos
	 * @param pIteracion
	 * @param pCol
	 */
	public int colFichasBloquear(int pX, int pY, int[] combinacion, int pCuantos, int pIteracion, int pCol) {
		// TODO - implement Tablero.colFichasBloquear
		throw new UnsupportedOperationException();
	}

	public boolean tableroLleno() {
		boolean lleno=true;
		for(int i=0;i<matriz[1].length;i++){
			Boolean pos=matriz[matriz.length-1][i];
			if(pos==null){
				lleno=false;
				return lleno;
			}
		}
		return lleno;
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public int getColindantes(int pX, int pY, boolean pColor) {
		// TODO - implement Tablero.getColindantes
		throw new UnsupportedOperationException();
	}

	public int[] getPosicionesPosibles() {
		// TODO - implement Tablero.getPosicionesPosibles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param pCont
	 * @param pC
	 * @param pColor
	 */
	public int numSeguidas(int x, int y, int pCont, int[] pC, boolean pColor) {
		// TODO - implement Tablero.numSeguidas
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColor
	 */
	public Collection<Integer> getOptimo(boolean pColor) {
		// TODO - implement Tablero.getOptimo
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene las posiciones del tablero que hacen 4 en raya.
	 * @author Ander Cejudo
	 * @param pX valor del eje x de la coordenada
	 * @param pY valor del eje y de la coordenada
	 * @param pColor color de la posición a mirar
	 * @return JSON con las posiciones ganadoras y en caso de que no es hayan encontrado, null
	 */
	public JSONArray getCoordenadasGanadoras(int pX, int pY, boolean pColor) {
		Collection<int[]> combinaciones = getPosiblesCombinaciones();
		JSONArray j = new JSONArray();
		ArrayList<int[]> coordenadas = new ArrayList<int[]>();
		boolean obtenidas = false;
		for (int[] c : combinaciones){
			coordenadas = listaSeguidas(pX,pY,1,new ArrayList<int[]>(),c,pColor);
			if (coordenadas.size() != 4) {
				c[0] *= -1;
				c[1] *= -1;
				coordenadas = listaSeguidas(pX, pY, 1, new ArrayList<int[]>(), c, pColor);
			}
			if (coordenadas.size() == 4){
				obtenidas = true;
				break;
			}
		}
		if (obtenidas){
			for (int[] c : coordenadas) {
				JSONObject o = new JSONObject();
				o.put("x",c[0]);
				o.put("y",c[1]);
				j.add(o);
			}
		}
		return j;
	}

	/**
	 * Método recursivo para obtener las coordenadas de hasta 4 fichas seguidas del mismo color, las coordenadas recibidas
	 * se aumentan con los valores de pCombinación en cada llamada recursiva.
	 * @param pX Coordenada del eje X
	 * @param pY Coordenada del eje Y
	 * @param pCont Contador de las veces que se ha llamado al método de forma recursiva
	 * @param pC Lista de las coordenadas del mismo color seguidas
	 * @param pCombinacion Combinación que hace variar las coordenadas pX y pY
	 * @param pColor Color que tienen que tener las fichas en cada coordenada
	 * @return Lista de n coordenadas (donde 0<|n|<5) seguidas del mismo color
	 */
	public ArrayList<int[]> listaSeguidas(int pX, int pY, int pCont, ArrayList<int[]> pC, int[] pCombinacion, boolean pColor) {
		if (posValida(pX,pY) && esColor(pX,pY,pColor) && pCont < 4) {
			int[] coordenada = {pX,pY};
			pC.add(coordenada);
			listaSeguidas(pX+pCombinacion[0],pY+pCombinacion[1],pCont+1,pC,pCombinacion,pColor);
		}
		return pC;
	}

	/**
	 * Obtiene las posibles combinaciones en las que se puede desplazar en el tablero (arriba, abajo, izquierda, derecha y las diagonales).
	 * Hay 8 direcciones en las que se puede mirar, pero con 4 y multiplicando por -1 se obtienen todas.
	 * @author Ander Cejudo
	 * @return lista que cotiene todas las combinaciones posibles para desplazarse por el tablero
	 */
	public Collection<int[]> getPosiblesCombinaciones() {
		ArrayList<int[]> combinaciones = new ArrayList<int[]>();
		int[] i1 = {1,0};
		int[] i2 = {0,1};
		int[] i3 = {1,1};
		int[] i4 = {1,-1};
		combinaciones.add(i1);
		combinaciones.add(i2);
		combinaciones.add(i3);
		combinaciones.add(i4);
		return combinaciones;
	}

}