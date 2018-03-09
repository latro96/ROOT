package helpCampos;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

import funciones.Funciones;

@WebServlet(name="HelpCampo", urlPatterns={"/HelpCampo"})
public class HelpCampoUrl extends HttpServlet{  
	Funciones fun=null;
	 
static final int PRE_FECHDIF = 90;	
static final int ID_OK   = 4; //c
static final int ID_PND  = 5;
static final int ID_BLOQ = 6; //r
static final int ID_NUE  = 17; //Autos nuevos
static final int ID_USA  = 18; //Autos usados

	public void doPost(HttpServletRequest req,HttpServletResponse res)  throws ServletException,IOException {
		fun =new Funciones(req);
		String pagina = req.getParameter("page");
		String rows =req.getParameter("rows");
		String ordenarcampo = req.getParameter("sidx");
		String ordenarmetodo = req.getParameter("sord");
	    String BusquedaValor = req.getParameter("BusquedaValor");
	    String BusquedaCampo = req.getParameter("BusquedaCampo");
	    String search_campo=req.getParameter("searchField");
	    String search_valor=req.getParameter("searchString");
	    String search_oper=req.getParameter("searchOper");
		String tipo=req.getParameter("tipo");
		String filtExt = req.getParameter("filtExt");
		HelpCampo hc=null;
		switch (tipo){
			case "CLI":
				hc = new HelpCampoCli(fun,req,null,ordenarcampo,ordenarmetodo,pagina,rows,
						search_campo, search_oper, search_valor,BusquedaValor,BusquedaCampo,filtExt);
				break;
			case "ART":
				hc = new HelpCampoArt(fun,req,null,ordenarcampo,ordenarmetodo,pagina,rows,
						search_campo, search_oper, search_valor,BusquedaValor,BusquedaCampo,filtExt);
				break;
		}

    	PrintWriter prt=res.getWriter();
    	res.setContentType("application/json"); 		    
	    try{	    	
			JSONObject jsonGrilla=hc.getJsonGrilla(); 		   
			prt.print(jsonGrilla.toString());
		}catch(Exception e){
	    	 System.err.println("Got an exception! ");
		     System.err.println(e.getMessage());
		     prt.println(e.getMessage());		   
	     }
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)  throws ServletException,IOException{
		fun =new Funciones(request);
		String tipo= request.getParameter("tipo"); //seg-cli-pre-hc-int-leg			
		String filtExt = request.getParameter("filtExt");
		String BusqVal = request.getParameter("BusqVal");
		String BusqCam = request.getParameter("BusqCam");
		HelpCampo hc=null;
		switch (tipo){
			case "CLI":
				 hc = new HelpCampoCli(fun, request);
				 break;
			case "ART":
				hc = new HelpCampoArt(fun, request);
				break;
		}

		request.setAttribute("filtExt",filtExt);
		request.setAttribute("hc", hc);
		request.setAttribute("BusqVal",BusqVal);
		request.setAttribute("BusqCam",BusqCam);
		
		RequestDispatcher rd=request.getRequestDispatcher("HelpCampo.jsp");
		rd.forward(request, response);
	}
}
