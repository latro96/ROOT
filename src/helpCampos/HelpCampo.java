package helpCampos;

<<<<<<< HEAD
import java.util.Map;

=======
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
>>>>>>> branch 'master' of https://github.com/latro96/ROOT.git
import org.json.simple.JSONObject;

import funciones.Funciones;

public abstract class HelpCampo {
	protected Funciones fun=null;	
	protected String campo = null;
	protected String ordenarCampo = null;
	protected String ordenarMetodo = null;
	protected String pagina = null;
	protected String cntRows = null;	
	protected String search_campo = null;
	protected String search_oper = null;
	protected String search_valor = null;
	protected String busquedaValor = null;
	protected String busquedaCampo = null;
	protected String filtExt = null;
	protected String rows="";
	
	/*	public  HelpCampoCli(Funciones func, HttpServletRequest req, String campo, String OrdenarCampo, String OrdenarMetodo, String Pagina, String CntRows, 
			 		String search_campo,  String search_oper,  String search_valor, String BusquedaValor, String BusquedaCampo,String FiltExt){
		this.fun=func;
		
		this.campo = "cli_nomb";
		this.ordenarMetodo = "asc";
		this.pagina = "1";
		this.cntRows = "10";	
		this.search_campo =search_campo;	
		this.search_oper = search_oper;	
		this.search_valor = search_valor;	
		this.busquedaValor =BusquedaValor;	
		this.busquedaCampo = BusquedaCampo;	
		this.filtExt = filtExt;	
		
		
		this.setCampo(campo);
		this.setOrdenarCampo(OrdenarCampo);
		this.setOrdenarMetodo(OrdenarMetodo);
		this.setPagina(Pagina);
		this.setCntRows(CntRows);
	}*/
	
	public void setCampo(String campo){
		this.campo = ( ( campo==null || campo.equals("") ) ? this.campo : campo );
	}
	
	public String getCampo(){
		return this.campo;
	}
	
	public void setOrdenarCampo(String OrdenarCampo){
		this.ordenarCampo = ( ( OrdenarCampo==null || OrdenarCampo.equals("") ) ? this.ordenarCampo : OrdenarCampo );
	}
	
	public String getOrdenarCampo(){
		return this.ordenarCampo;
	}
	
	public void setOrdenarMetodo(String OrdenarMetodo){
		this.ordenarMetodo = ( ( OrdenarMetodo==null || ordenarMetodo.equals("") ) ? this.ordenarMetodo : OrdenarMetodo );
	}
	
	public String getOrdenarMetodo(){
		return this.ordenarMetodo;
	}
	
	public void setPagina(String Pagina){
		this.pagina = ( ( Pagina==null || Pagina.equals("") ) ? this.pagina : Pagina );
	}
	
	public String getPagina(){
		return this.pagina;
	}
	
	public void setCntRows(String CntRows){
		this.cntRows = ( ( CntRows==null || CntRows.equals("") ) ? this.cntRows : CntRows );
	}
	
	public String getCntRows(){
		return this.cntRows;
	}
	
	public String getSentenciaWhere(){
		String sentenciaWhere="";
		String search_operador="";
		if(!(this.search_valor == null || this.search_valor.length() == 0)){
			switch (search_oper ) {
			case "eq" : 
				search_operador ="=" ;
				break;
			case "ne" :
				search_operador ="<>" ;
				break;
			case "lt" : 
				search_operador ="<" ;
				break;
			case "le" :
				search_operador ="<=" ;
				break;
			case "gt" :
				search_operador =">" ;
				break;
			case "ge" : 
				search_operador =">=" ;
				break;
			case "bw" : 
				search_operador ="LIKE" ;
				this.search_valor+="%";
				break;
			case "bn" :
				search_operador ="NOT LIKE" ;                
				this.search_valor+="%";
				break;
			case "in" :
				search_operador ="LIKE" ;
				this.search_valor="%"+this.search_valor+"%";
				break;
			case "ni" :
				search_operador ="NOT LIKE" ;
				this.search_valor="%"+this.search_valor+"%";
				break;
			case "ew" : 
				search_operador ="LIKE" ;
				this.search_valor="%"+this.search_valor;
				break;
			case "en" : 
				search_operador ="NOT LIKE" ;
				this.search_valor=" %"+this.search_valor;
				break;
			case "cn" :
				search_operador ="LIKE" ;
				this.search_valor="%"+this.search_valor+"%";
				break;
			case "nc" :
				search_operador ="NOT LIKE" ;
				this.search_valor="%"+this.search_valor+"%";
				break;
			}
			sentenciaWhere=" where "+this.search_campo+" "+search_operador+" '"+this.search_valor+"'";
		}
		return sentenciaWhere;
	}
	
	public String getEmpieza(){
		int pag= -1+Integer.parseInt(this.getPagina());	    
	    int rpaux = 0+Integer.parseInt(this.getCntRows());	    
	    return String.valueOf( pag * rpaux);	
	}
	
	public String getTermina(){
	    return String.valueOf( Integer.parseInt( this.getPagina() ) *Integer.parseInt( this.getCntRows() ) );	    
	}
	
	public abstract String getFiltro();
	
	public abstract String  getTabla();
	
	public abstract Map<String,String> getColum();
	
	public JSONObject getJsonGrilla(){
		return this.fun.Grilla(this.getTabla(), this.getEmpieza(), this.getTermina(), this.getPagina(), this.getCntRows());
	}
	
	public String getRows(){
		return this.getJsonGrilla().toString();
	}
	
}
