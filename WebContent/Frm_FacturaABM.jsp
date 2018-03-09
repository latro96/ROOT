<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="funciones.Funciones"%>
<%
	Funciones fun = new Funciones(request);
	String idForm = "Frm_FacturaABM";
	String idGrilla = "FacturaABM";
	String URL = "'./Frm_FacturaABM'";
	String modo=(String) request.getAttribute("modo");
	String Factura=(String) request.getAttribute("Factura");
%>
<div id="<%=idForm%>" data-popup="true" class="backmodal formulario">
	<style type="text/css">
		#form {
			position: relative;
			-moz-box-sizing: content-box;
			-webkit-box-sizing: content-box;
			box-sizing: content-box;
			margin: 0px auto;
			width: 100%;
			z-index: 1;
		}
		
		.form-control {
    		margin: 0px auto;
    	}
    	
    	
		
		#jqgridSearchForm {
			position: relative;
			-moz-box-sizing: content-box;
			-webkit-box-sizing: content-box;
			box-sizing: content-box;
			margin: 0px auto;
			width: 100%;
			z-index: 1;
		}
		
		#<%=idForm%>>div {
			width: 850px;
			margin-top: 20px;
		}
		
		#<%=idForm%> #fld_totales, #<%=idForm%> #fld_totales .fila * {
			float: right;
		}
	</style>

	<div class="modal" data-tmodal="alerta">
		<div class="modal-header">
			<h5 class="modal-title" id="exampleModalLabel"><span id="Titulo"></span> Factura</h5>
			<button type="button" type="button" class="close"
				onclick="cerrarFormu('<%=idForm%>');">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="modal-body">
			<div class="d-block">
				<fieldset>
					<div class="fila">						
						<input id="modo" class="form-control campo" type="hidden">
						<input id="cmp_compa" class="form-control campo" type="hidden">
						<input id="cmp_codig" class="form-control campo" type="hidden">					
						<span class="form-control with-10-00">Fecha</span>
						<input id="cmp_fecha" class="form-control with-10-00 campo" type="text" maxlength="4">
						<span class="form-control with-10-00">N°</span>
						<select id="cmp_letra" class="form-control with-10-00 campo">						
							<option value="A">A</option>
							<option value="B">B</option> 
							<option value="C">C</option>
						</select>
						<select id="cmp_ptvta" class="form-control with-10-00 campo">
							<%=fun.GetHTMLOtion("ptv_codig", "right(concat(\"0000\",ptv_codig),4)", "dbPuntosVentas","ptv_compa", " ptv_activ=1 ")%>
						</select>
						<input id="cmp_nroco" class="form-control with-20-00 campo" type="text" maxlength="8">
					</div>
				</fieldset>
				<fieldset>
  					<legend>Cliente</legend>
					<div class="fila">					
						<span class="form-control with-10-00">Nombre</span>
						<input id="cmp_codcl" class="form-control with-6-30 campo" type="text" maxlength="30">						
						<button type="button" class="btn blanco form-control with-3-70" id="btn_HcCli">
							<img src="/img/iconos/glyphicons-28-search.png"	style="height: auto; filter: invert(55%);">
						</button>
						<input id="cli_nombr" class="form-control with-50-00 campo" type="text" maxlength="30">	
						<span class="form-control with-5-00">IVA</span>
						<select id="cmp_fciva" class="form-control with-25-00 campo">
							<%=fun.GetHTMLOtion("iva_codig", "iva_nombr", "dbCondIva", "", "")%>
						</select>
					</div>					
					<div class="fila">
						<span class="form-control with-10-00">Telefono</span>
						<input id="cmp_ftele" class="form-control with-25-00 campo" type="text" maxlength="30">
						<span class="form-control with-10-00">Celular</span>						
						<input id="cmp_fcelu" class="form-control with-25-00 campo" type="text" maxlength="30">
						<span class="form-control with-5-00">Doc.</span>
						<select id="cmp_ftdoc" class="form-control with-10-00 campo">
							<%=fun.GetHTMLOtion("doc_codig", "doc_nombre", "dbTipoDocumentos", "", "")%>
						</select>
						<input id="cmp_fndoc" class="form-control with-15-00 campo" type="text" maxlength="11">						
					</div>
					<div class="fila">
						<span class="form-control with-10-00">Direción</span>
						<input id="cmp_fdire" class="form-control with-60-00 campo" type="text" maxlength="60">		
					</div>
				</fieldset>
				<fieldset id="fld_<%=idGrilla %>" >
  					<legend>Detalle</legend>
  					<div class="fila gris T-blanco rounded" style="height: 40px;padding: 4px 10px;">
  						<div class="tool d-none"></div>
						<div class="tool tool-boton" data-modo="ALTA">
							<img src="/img/iconos/glyphicons-433-plus.png"
								style="width: auto; filter: invert(55%);">
							<div class="overlay gris">
								<div class="textimg">Crear</div>
							</div>
						</div>
						<div class="tool tool-boton" data-modo="MODI">
							<img src="/img/iconos/glyphicons-31-pencil.png"
								style="width: auto; filter: invert(55%);">
							<div class="overlay gris">
								<div class="textimg ">Modificar</div>
							</div>
						</div>
						<div class="tool tool-boton" data-modo="BAJA">
							<img src="/img/iconos/glyphicons-208-remove.png"
								style="width: auto; filter: invert(55%);">
							<div class="overlay gris">
								<div class="textimg">Eliminar</div>
							</div>
						</div>
					</div>				
					<div class="d-block">
						<table id="<%=idGrilla%>"></table>
						<div id="<%=idGrilla%>_pie"></div>
					</div>
  				</fieldset>
				<fieldset id="fld_totales" class="with-50-00">
  					<legend>Totales</legend>
					<div class="fila">
						<input id="cmp_fdire" class="form-control with-30-00 campo" type="text" maxlength="60">
						<span class="form-control with-30-00">Neto</span>
					</div>	
					<div class="fila">
						<input id="cmp_fdire" class="form-control with-30-00 campo" type="text" maxlength="60">
						<span class="form-control with-30-00">IVA</span>
					</div>	
					<div class="fila">
						<input id="cmp_fdire" class="form-control with-30-00 campo" type="text" maxlength="60">	
						<span class="form-control with-30-00">Total</span>
					</div>
				</fieldset>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" id="btn_confirmar">Guardar Cambios</button>
			<button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="cerrarFormu('<%=idForm%>');">Cerrar</button>
		</div>
	</div>

	<script type="text/javascript">       
      	var idGrilla="<%=idGrilla%>";
       	var NidGrilla = "#" + idGrilla;
       	var formulario = $("#<%=idForm%>");
       	$("#modo",formulario).val("<%=modo%>");
       	formulario.modo="<%=modo%>";
       	<%=Factura%>
       	stringFrom="<div id=\"form\">\n"+
							out.println(fun.buttonString("btn_rev", "form-control ", "width:20px; height: 18px; padding: 1px;", "button",
		           					"se apreto", "<img src=\"/img/iconos/glyphicons-208-remove.png\" style=\"width: 16px; \">", "")) +
		           			out.println(fun.inputString("ptv_compa", "form-control dato", "width: 0px;", "hidden", "value=\""+String.valueOf(fun.compania)+"\"")) +		
		           			out.println(fun.inputString("ptv_codig", "form-control dato", "width: 29px;", "text", "placeholder=\"Codigo\"")) +
		           			out.println(fun.inputString("ptv_nombr", "form-control dato", "width: 225px;", "text",	"placeholder=\"Nombre\"")) +
		           			out.println(fun.selectString("ptv_tipop",  "form-control dato", "width: 110px;", "", "", ""))+
		           			out.println(fun.inputString("ptv_certi", "form-control dato", "width: 225px;", "text",	"placeholder=\"Certificado\"")) +
		           			out.println(fun.inputString("ptv_activ", "form-control dato", "width: 36px;", "checkbox",	"placeholder=\"activo\"")) +
		           			out.println(fun.buttonString("btn_act", "form-control ", "width:14px; height: 18px;  padding: 1px;", "button",
		           					"se apreto", "<img src=\"/img/iconos/check.svg\" style=\"width: 16px; \">", "")) +
		           			"</div>";
        $(document).ready(function(){	 		
        	Factura.forEach(function(value,index){	
        		if (value=="true" || value=="false"){
        			$("#"+index,formulario).first().prop("checked",(value=="true"?true:false));            			
        		}else{
        			$("#"+index,formulario).first().val(value);
        		}	
        	});
        	

        	var modoTitulo="";
        	switch(formulario.modo){
	        	case "CONS":
	        		modoTitulo="Consultar";	  
	        		$("#btn_confirmar",formulario).hide();
		        break;
	        	case "BAJA":
	        		modoTitulo="Eliminar";	
		        break;
	        	case "MODI":
	        		modoTitulo="Modificar";	        
		        break;
	        	case "ALTA":
	        		modoTitulo="Crear";	        
		        break;	        
		        default:
			        break;
	        }       
        	$(".modal",formulario).draggable();
    		$("#btn_confirmar",formulario).html(modoTitulo);
        	formulario.show();   
        	
        	$("#btn_confirmar",formulario).unbind("click").click(function(){
        		if (validarCli()){
        			cargando();
				    $.ajax({
				    	dataType:'json',
				    	data: $(".campo", formulario).serialize(),
				    	type:'POST',
				    	url:"/<%=idForm%>", 
				    	success:function(data){		
				    		cerrarAlerta();//mensaje cargando
					    	if(data.error !=0){
						    	abrirAlerta("ERR", data.msg);//hubo  un error
					    	}else{
					    		cerrarFormu('<%=idForm%>'); // cierra le fomrulario
					    	}
				    	},
				    	error:function(data){		
				    		cerrarAlerta();
					    	abrirAlerta("ERR", data.msg);
				    	}
				    });
        		}
        	}); 
        	$("#Titulo",formulario).html(modoTitulo);
        	$("#btn_confirmar",formulario).unbind("click").click(function(){
            	HelpCampo("ART","","","",false,"",
            	          function(row,id){
            				alert(id);
            				console.log(JSON.stringify(row));
            			});
        	});
        	 Grilla();
        });
        

    	
        function Grilla(){	        
	        $(NidGrilla).jqGrid({
	        	datatype : "jsonstring",
	        	mtype:'GET', 
	        	colNames:['Compania','cód','Cant.','Cod.','Nombre','Precio','Precio','Total','Total'],
	        	colModel:[
	        		{name:'cmd_compa', index:'cmd_compa', width:0, hidden:true},
	        		{name:'cmd_codig', index:'cmd_codig', width:0, hidden:true},
	        		{name:'cmd_canti', index:'cmd_canti', width:10, hidden:false, align:'right'},
	        		{name:'cmd_artic', index:'cmd_artic', width:20, hidden:false},
	        		{name:'art_nombr', index:'art_nombr', width:60,hidden:false},
	        		{name:'cmd_nprec', index:'cmd_nprec', width:40,hidden:false, align:'right'},
	        		{name:'cmd_fprec', index:'cmd_fprec', width:40,hidden:true, align:'right'},
	        		{name:'cmd_nstot', index:'cmd_nstot', width:40,hidden:false, align:'right'},
	        		{name:'cmd_fstot', index:'cmd_fstot', width:40,hidden:true, align:'right'}
	        		],
		        	width: ($("#fld_<%=idGrilla%>").width()),
		        	height: 200,
	        	rowNum:'100',
	        	datastr: "{}",
	        	rowList:[10, 15, 20, 25, 50, 75, 100, 150, 200, 250, 500, 750],
	        	pager:NidGrilla + '_pie',
	        	sortname:'cmd_codig',
	        	viewrecords:true,
	        	sortorder:"desc",
	        	hidegrid:false,
	        	title:false,	        	
	        	gridComplete:function(){
	        		$('tbody [role="row"]').each(function(id, val){
	        			if(id % 2 == 0){
	        				$(NidGrilla + ' #' + id).css('background-color', 'rgb(224, 224, 224)');
	        			}
	        		});
	        		$("#jqgridSearchForm").remove();
	        		$("#form",formulario).remove();
	        		$(".ui-jqgrid-bdiv",formulario).prepend(stringFrom);
	        		$("tr.jqgrow.ui-row-ltr.ui-widget-content").first().trigger("click");
	        		$(NidGrilla).focus();
	        	}, 
	        	ondblClickRow:function(id){
	        		/*var ret = $(NidGrilla).jqGrid('getRowData', id);
	        		$.each(Object.values(ret), function(i, val){
	        			$(".dato")[i].value = val;
	        		})*/
	        	},
	        	loadBeforeSend: function () {
	        		var x=this;	        	
	        	    $(this).closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable>thead>tr>th").each(function(index, elem){
	        	    	$(elem).css("text-align", (x.p.colModel[index].align===undefined? "left" : x.p.colModel[index].align));
	        	    });
	        	},
	        	caption:""
	        });
	        $(".ui-jqgrid-titlebar").hide();	 
	        $( document ).resize(function(){  
	      	  reSizeGrid(idGrilla,($("#Cuerpo").width()-10),($("#Cuerpo").height()-80));
	      	});
        }

	</script>	
</div>