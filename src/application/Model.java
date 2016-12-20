package application;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import bean.Country;
import db.DBConnect;
import db.Dao;

public class Model {
	
	Dao dao = new Dao();
	private UndirectedGraph <Country, DefaultEdge> grafo ;
	
	public List<Country> getCountry(){
		List<Country> city = new LinkedList<Country>();
		city = dao.getAllCity();
		return city;	
	}
	
	public List<Country> getConfini(Country c ,int contType, int year){
		List<Country> cit = new LinkedList<Country>();
		cit = dao.getBorderingCountries(c, contType, year);
		return cit;	
	}
	
	
	public void buildGraph(){
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		for(Country c : getCountry()){
			grafo.addVertex(c);
		}	
		for(Country c : grafo.vertexSet()){
			for(Country c1 : getConfini(c, 3, 2006) ){   //xke è 3? cos'è conttype?
				grafo.addEdge(c, c1);
			}
		}
		System.out.println(grafo.toString());
	}
	
	public UndirectedGraph<Country, DefaultEdge> getGrafo(){
		return grafo;
	}
	
	//calcolo le componenti connesse
	//so : calcolo i connessi: == trovo tutti i connessi

	public List<Country> getTrovoConnessi(Country c ){          //calcolo tutti gli stati raggiungibili da c  
		List<Country> connessi= new LinkedList<Country>();
		BreadthFirstIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<Country, DefaultEdge>(this.grafo, c);
		while(visita.hasNext()){
			Country c1 = visita.next();
			connessi.add(c1);
			
		}
		return connessi;
	}
	
	
	
	
	
	
	
	
	public List<Country> calcolaPercorso(Country c1 , Country c2){
		DijkstraShortestPath<Country, DefaultEdge> percorso = new DijkstraShortestPath<Country, DefaultEdge>(grafo, c1, c2);  //chiamo prima buildGraph e poi calcoloPercorso?
		GraphPath<Country, DefaultEdge> path = percorso.getPath();
		if(path == null){
			return null;     //percorso non esiste, le due nazioni non sono raggiungibili via terra
		}
		List<Country> nazionePerLeQualiDevoPassare = Graphs.getPathVertexList(path);
		return nazionePerLeQualiDevoPassare;
		
	}
	
	
	public Integer getComponentiConnesse(){
		int num=0;
		buildGraph();
		
		return num;
	}
	
	
//	public static void main(String [] args){
//		Model m = new Model();
//		m.buildGraph();
//	}
	
	

}
