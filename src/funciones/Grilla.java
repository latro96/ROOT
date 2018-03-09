package funciones;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Grilla {
	protected int indice=0;
	protected String tipo="";
	protected String titulo="";
	protected ArrayList<Columna> columnas=new ArrayList<Columna>();
	protected String alto="0px";
	protected String ancho="0pc";
	protected String funcionDblClick="function(){}";
	protected String funcionClick="function(){}";
	protected String functionGridComplete ="function(){}";
	protected String functionBeforeRequest ="function(){}";
	protected String sql="";
	protected String rows="[]";
	protected String divForm="";
	protected String dFSize =null;
	protected boolean actualizable=true;
	public Grilla(){}
	
	public Grilla(int indice, String tipo, String titulo, ArrayList<Columna> columnas, String alto, String ancho,
					String funcionDblClick, String funcionClick, String sql,String divForm,String dFSize){
		this.indice=indice;
		this.tipo=tipo;
		this.titulo=titulo;
		this.columnas=columnas;
		this.alto=alto;
		this.ancho=ancho;
		this.funcionDblClick=(funcionDblClick.equals("")?this.funcionDblClick:funcionDblClick);
		this.funcionClick=(funcionClick.equals("")?this.funcionClick:funcionClick);
		this.sql=sql;
		this.divForm=divForm;
		this.dFSize=dFSize;
	}
	public Grilla(int indice, String tipo, String titulo,  String alto, String ancho,
			String funcionDblClick, String funcionClick, String sql){
		this.indice=indice;
		this.tipo=tipo;
		this.titulo=titulo;
		this.columnas= new ArrayList<Columna>();
		this.alto=alto;
		this.ancho=ancho;
		this.funcionDblClick=(funcionDblClick.equals("")?this.funcionDblClick:funcionDblClick);
		this.funcionClick=(funcionClick.equals("")?this.funcionClick:funcionClick);
		this.sql=sql;
		this.divForm="";
		this.dFSize="";
}
	
	public void setIndice( int indice){
		this.indice=indice;
	}
	public void setTipo(String tipo){
		this.tipo=tipo;
	}
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
	public void setAlto(String alto){
		this.alto=alto;
	}
	public void setAncho(String ancho){
		this.ancho=ancho;
	}
	public void setFuncionDblClick(String funcionDblClick){
		this.funcionDblClick=funcionDblClick;
	}	
	public void setFuncionClick(String funcionClick){
		this.funcionClick=funcionClick;
	}	
	public void setFunctiongridComplete(String functionGridComplete){
		this.functionGridComplete = functionGridComplete;
	}
	public void setFunctionBeforeRequest(String functionBeforeRequest){
		this.functionBeforeRequest = functionBeforeRequest;
	}
	public void setDivForm(String divForm){
		this.divForm=divForm;
	}
	public void setDFSize(String df){
		this.dFSize=df;
	}
	public void setActualizable(boolean actualizable){
		this.actualizable=actualizable;
	}	
	public void addColumna(Columna col){		
		columnas.add(col);
	}
	public void addColumna(String titulo,String name,String index,String ancho,String align,Boolean hidden){		
		if (ancho.contains("%")){
			ancho= String.valueOf(Math.round(Double.parseDouble(ancho.split("%")[0])*100/Double.parseDouble(this.ancho.split("%")[0])));
		}
		columnas.add(new Columna(titulo,name,index,ancho,align,hidden));
		
	}
	public void delColumna(String index){
		Columna colActual;
		if (!columnas.isEmpty()){
			Iterator<Columna> it = columnas.iterator();
			while (it.hasNext()) {
				colActual=it.next();
				if (colActual.getIndex().equals(index)){
					it.remove();
				}
            }
		}
	}
	public void setSql(String sql){
		this.sql=sql;
	}
	
	
	
	public int getIndice(){
		return this.indice;
	}
	public String getTipo(){
		return this.tipo;
	}
	public String getTitulo(){
		return this.titulo;
	}
	public String getAlto(){
		return this.alto;
	}
	public String getAncho(){
		return this.ancho;
	}
	public String getFuncionDblClick(){
		return this.funcionDblClick;
	}	
	public String getFuncionClick(){
		return this.funcionClick;
	}	
	public String getFunctionGridComplete(){
		return this.functionGridComplete ;
	}
	public String getFunctionBeforeRequest(){
		return this.functionBeforeRequest ;
	}
	public String getDivForm(){
		return this.divForm;
	}
	public String getDFSize(){
		return this.dFSize;
	}
	public Boolean getActualizable(){
		return this.actualizable;
	}
	public Columna getColumna(String index){
		Columna getCol=null;
		Columna colActual=null;
		if (!columnas.isEmpty()){
			Iterator<Columna> it = columnas.iterator();
			while (it.hasNext()) {
				colActual=it.next();
				if (colActual.getIndex().equals(index)){
					getCol=colActual;
				}
            }
		}
		return getCol;
	}
	public String GetColTitulos(){
		String colTitulo="[";
		int cols=columnas.size();
		int i=0;
		while (cols>i){
			colTitulo+=(colTitulo.equals("[")?"":",");
			colTitulo+="\""+columnas.get(i).getTitulo()+"\"";
			i++;
		}
		colTitulo+="]";
		return colTitulo; 
	}
	
	public String GetColModel(){
		String colTitulo="[";
		int cols=columnas.size();
		int i=0;
		while (cols>i){
			colTitulo+=(colTitulo.equals("[")?"":",");
			colTitulo+=columnas.get(i).getColModel();
			i++;
		}
		colTitulo+="]";
		return colTitulo; 
	}
	
	public String getSql(){
		return this.sql;
	}
	
	public String getRows() {
		// TODO Auto-generated method stub
		return this.rows;
	}
	
	
	public abstract  void setRows(ResultSet rs);
	
	public String getStringJavaScript(){
		String js="";
        int indice=this.indice;
        js +="Grilla["+indice+"]=new Map();";
        js +="Grilla["+indice+"].set('alto','"+this.getAlto()+"');\n";
        js +="Grilla["+indice+"].set('ancho','"+this.getAncho()+"');\n";
        js +="Grilla["+indice+"].set('titu','"+this.getTitulo()+"');\n";
        js +="Grilla["+indice+"].set('indi','"+indice+"');\n";
        js +="Grilla["+indice+"].set('colNames',"+this.GetColTitulos()+");\n";
        js +="Grilla["+indice+"].set('colModel',"+this.GetColModel()+");\n";
        js +="Grilla["+indice+"].set('ondblClickRow',"+this.getFuncionDblClick()+");\n";
        js +="Grilla["+indice+"].set('onClickRow',"+this.getFuncionClick()+");\n";
        js +="Grilla["+indice+"].set('beforeRequest',"+this.getFunctionBeforeRequest()+");\n";
        js +="Grilla["+indice+"].set('rows',"+this.rows+");\n";
        js +="Grilla["+indice+"].set('divForm','"+this.getDivForm()+"');\n";
        js +="Grilla["+indice+"].set('dFSize','"+this.getDFSize()+"');\n";
        js +="Grilla["+indice+"].set('functionGridComplete',"+this.getFunctionGridComplete()+");\n";
        js +="Grilla["+indice+"].set('actualizable',"+this.getActualizable()+");\n"; 
        js +="indices.set('"+this.tipo+"',"+indice+");\n";        
		
		return js;
	}


	
	protected static final String ajaxGridOptions = "{}";				//This option allows to set global ajax settings for the grid when requesting data. Note that with this option it is possible to overwrite all current ajax settings in the grid including the <code>error</code>, <code>complete</code> and <code>beforeSend</code> events.
	protected static final String ajaxSelectOptions = "{}";				//This option allows to set global ajax settings for the select element when the select is obtained via <code>dataUrl</code> option in <code>editoptions</code> or <code>searchoptions</code> objects
	protected static final String  altclass = "ui-priority-secondary"; 	//The class that is used for applying different styles to alternate (zebra) rows in the grid. You can construct your own class and replace this value. This option is valid only if the <code>altRows</code> option is set to true
	protected static final Boolean  altRows =  false;					//Set a zebra-striped grid (alternate rows have different styles)
	protected static final Boolean  autoencode = false;					//When set to true encodes (html encode) the incoming (from server) and posted data (from editing modules). For example <code>&lt;</code> will be converted to <code>&amp;lt;</code>.
	protected Boolean  autowidth = false;								//When set to true, the grid width is recalculated automatically to the width of the parent element. This is done only initially when the grid is created. In order to resize the grid when the parent element changes width you should apply custom code and use the <code>setGridWidth</code> method for this purpose
	protected String  caption = ""			;							//Defines the caption for the grid. This caption appears in the caption layer, which is above the header layer (see <a href="/jqgridwiki/doku.php?id=wiki:how_it_works" class="wikilink1" title="wiki:how_it_works">How It Works</a>). If the String is empty the caption does not appear.
	protected int  cellLayout = 5;										//This option determines the padding + border width of the cell. Usually this should not be changed, but if custom changes to the <code>td</code> element are made in the grid css file, this will need to be changed. The initial value of 5 means paddingLef(2) + paddingRight (2) + borderLeft (1) = 5
	protected static final Boolean  cellEdit = false;					//Enables (disables) cell editing. See <a href="/jqgridwiki/doku.php?id=wiki:cell_editing" class="wikilink1" title="wiki:cell_editing">Cell Editing</a> for more details
	protected static final String  cellsubmit = "remote";				//Determines where the contents of the cell are saved. Possible values are <code>remote</code> and <code>clientArray</code>. See <a href="/jqgridwiki/doku.php?id=wiki:cell_editing" class="wikilink1" title="wiki:cell_editing">Cell Editing</a> for more details.
	protected static final String  cellurl = null;						//the url where the cell is to be saved. See <a href="/jqgridwiki/doku.php?id=wiki:cell_editing" class="wikilink1" title="wiki:cell_editing">Cell Editing</a> for more details
	protected String cmTemplate =  null ;								//Defines a set of properties which override the default values in <code>colModel</code>. For example if you want to make all columns not sortable, then only one propery here can be specified instead of specifying it in all columns in <code>colModel</code> 
	protected  String  colModel = "[]";									//Array which describes the parameters of the columns.This is the most important part of the grid. For a full description of all valid values see <a href="/jqgridwiki/doku.php?id=wiki:colmodel_options" class="wikilink1" title="wiki:colmodel_options"> colModel API</a>. 
	protected  String  colNames = "[]";									//An array in which we place the names of the columns. This is the text that appears in the head of the grid (header layer). The names are separated with commas. Note that the number of elements in this array should be equal of the number elements in the <code>colModel</code> array.
	protected static final String  data =  "[]";						//An array that stores the local data passed to the grid. You can directly point to this variable in case you want to load an array data. It can replace the <code>addRowData</code> method which is slow on relative big data
	protected static final String  datastr = null;						//The String of data when datatype parameter is set to <a href="/jqgridwiki/doku.php?id=wiki:retrieving_data#"xml"_String" class="wikilink1" title="wiki:retrieving_data"> "xml"String</a> or <a href="/jqgridwiki/doku.php?id=wiki:retrieving_data#json_String" class="wikilink1" title="wiki:retrieving_data"> jsonString</a>
	protected static final String  datatype = "xml";					//Defines in what format to expect the data that fills the grid. Valid options are <code>"xml"</code> (we expect data in "xml" format), <code>"xml"String</code> (we expect "xml" data as String), <code>json</code> (we expect data in JSON format), <code>jsonString</code> (we expect JSON data as a String), <code>local</code> (we expect data defined at client side (array data)), <code>javascript</code> (we expect javascript as data), <code>function</code> (custom defined function for retrieving data), or <code>clientSide</code> to manually load data via the <code>data</code> array. See <a href="/jqgridwiki/doku.php?id=wiki:colmodel_options" class="wikilink1" title="wiki:colmodel_options"> colModel API</a> and <a href="/jqgridwiki/doku.php?id=wiki:retrieving_data" class="wikilink1" title="wiki:retrieving_data"> Retrieving Data</a>
	protected static final Boolean  deepempty = false;					//This option should be set to <code>true</code> if an event or a plugin is attached to the table cell. The option uses jQuery empty for the the row and all its children elements. This of course has speed overhead, but prevents memory leaks. This option should be set to <code>true</code> if a sortable rows and/or columns are activated.
	protected static final Boolean  deselectAfterSort = true;			//Applicable only when we use <code>datatype : local</code>. Deselects currently selected row(s) when a sort is applied.
	protected String  direction = "ltr";								//Determines the direction of text in the grid. The default is <code>ltr</code> (Left To Right). When set to <code>rtl</code>  (Right To Left) the grid automatically changes the direction of the text. It is important to note that in one page we can have two (or more) grids where the one can have direction <code>ltr</code> while the other can have direction <code>rtl</code>. This option works only in Firefox 3.x versions and Internet Explorer versions &gt;=6. Currently Safari, Google Chrome and Opera do not completely support changing the direction to <code>rtl</code>. The most common problem in Firefox is that the default settings of the browser do not support <code>rtl</code>. In order change this see this <a href="/jqgridwiki/doku.php?id=wiki:howto_grid_base" class="wikilink1" title="wiki:howto_grid_base">HOW TO</a> section in this chapter .
	protected static final String  editurl = null;						//Defines the url for inline and form editing. May be set to <code>clientArray</code> to manually post data to server, see <a href="/jqgridwiki/doku.php?id=wiki:inline_editing_s_clientarray" class="wikilink2" title="wiki:inline_editing_s_clientarray" rel="nofollow">Inline Editing</a>. 
	protected static final  String  emptyrecords = "see lang file";		//The String to display when the returned (or the current) number of records in the grid is zero. This option is valid only if <code>viewrecords</code> option is set to <code>true</code>.
	protected Boolean  ExpandColClick = true;							//When <code>true</code>, the tree grid (see <code>treeGrid</code>) is expanded and/or collapsed when we click anywhere on the text in the expanded column. In this case it is not necessary to click exactly on the icon. 
	protected String  ExpandColumn = null;								//Indicates which column (name from <code>colModel</code>) should be used to expand the tree grid. If not set the first one is used. Valid only when the <code>treeGrid</code> option is set to <code>true</code>.
	protected Boolean  footerrow = false;								//If set to <code>true</code> this will place a footer table with one row below the gird records and above the pager. The number of columns equal those specified in <code>colModel</code>
	protected Boolean  forceFit = false;								//If set to <code>true</code>, and a column"s width is changed, the adjacent column (to the right) will resize so that the overall grid width is maintained (e.g., reducing the width of column 2 by 30px will increase the size of column 3 by 30px). In this case there is no horizontal scrollbar. <strong>Note:</strong> This option is not compatible with <code>shrinkToFit</code> option - i.e if <code>shrinkToFit</code> is set to false, <code>forceFit</code> is ignored.
	protected String  gridstate = "visible";							//Determines the current state of the grid (i.e. when used with <code>hiddengrid</code>, <code>hidegrid</code> and <code>caption</code> options). Can have either of two states: <code>visible</code> or <code>hidden</code>. 
	protected static final Boolean  gridview = false;					//In the previous versions of jqGrid including 3.4.X, reading a relatively large data set (number of rows &gt;= 100 ) caused speed problems. The reason for this was that as every cell was inserted into the grid we applied about 5 to 6 jQuery calls to it. Now this problem is resolved; we now insert the entry row at once with a jQuery append. The result is impressive - about 3 to 5 times faster. What will be the result if we insert all the data at once? Yes, this can be done with a help of <code>gridview</code> option  (set it to <code>true</code>). The result is a grid that is 5 to 10 times faster. Of course, when this option is set to <code>true</code> we have some limitations. If set to <code>true</code> we can not use <code>treeGrid</code>, <code>subGrid</code>, or the <code>afterInsertRow</code> event. If you do not use these three options in the grid you can set this option to <code>true</code> and enjoy the speed. 
	protected static final Boolean  grouping = false;					//Enables grouping in grid. Please refer to the <a href="/jqgridwiki/doku.php?id=wiki:grouping" class="wikilink1" title="wiki:grouping">Grouping</a> page. 
	protected Boolean  headertitles = false;							//If the option is set to <code>true</code> the title attribute is added to the column headers. 
	protected String  height = "150";										//The height of the grid. Can be set as number (in this case we mean pixels) or as percentage (only 100% is acceped) or value of <code>auto</code> is acceptable. 
	protected Boolean  hiddengrid = false;								//If set to <code>true</code> the grid is initially is hidden. The data is not loaded (no request is sent) and only the caption layer is shown. When the show/hide button is clicked for the first time to show grid, the request is sent to the server, the data is loaded, and grid is shown. From this point we have a regular grid. This option has effect only if the <code>caption</code> property is not empty and the <code>hidegrid</code> property (see below) is set to <code>true</code>. 
	protected Boolean  hidegrid = true;									//Enables or disables the show/hide grid button, which appears on the right side of the caption layer (see <a href="/jqgridwiki/doku.php?id=wiki:how_it_works" class="wikilink1" title="wiki:how_it_works"> How It Works</a>). Takes effect only if the <code>caption</code> property is not an empty String. 
	protected static final Boolean  hoverrows = true;					//When set to <code>false</code> the effect of mouse hovering over the grid data rows is disabled.
	protected static final String  idPrefix = "";						//When set, this String is added as prefix to the id of the row when it is built. 
	protected static final Boolean  ignoreCase = false;					//By default the local searching is case-sensitive. To make the local search and sorting not case-insensitive set this options to <code>true</code> 
	protected static final String inlineData ="{}";						//an array used to add content to the data posted to the server when we are in inline editing. 
	protected String  jsonReader = "[]" ;									//An array which describes the structure of the expected json data. For a full description and default setting, see <a href="/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data" class="wikilink1" title="wiki:retrieving_data"> Retrieving Data JSON Data</a>
	protected int  lastpage = 0;										//Gives the total number of pages returned from the request. If you use a function as datatype, your_grid.setGridParam({lastpage: your_number}) can be used to specify the max pages in the pager. 
	protected int  lastsort = 0;										//Readonly property. Gives the index of last sorted column beginning from 0. 
	protected Boolean  loadonce = false;								//If this flag is set to <code>true</code>, the grid loads the data from the server only once (using the appropriate datatype). After the first request, the datatype parameter is automatically changed to <code>local</code> and all further manipulations are done on the client side. The functions of the pager (if present) are disabled.
	protected String  loadtext = "Loading…";							//The text which appears when requesting and sorting data. This parameter is located in language file. 
	protected static final String  loadui = "enable";					//This option controls what to do when an ajax operation is in progress.<br/><strong>disable</strong> - disables the jqGrid progress indicator. This way you can use your own indicator.<br/><strong>enable</strong> (default) - shows the text set in the <code>loadtext</code> property (default value is <code>Loading…</code>) in the center of the grid. <br/><strong>block</strong> - displays the text set in the <code>loadtext</code> property and blocks all actions in the grid until the ajax request completes. Note that this disables paging, sorting and all actions on toolbar, if any. 
	protected static final String  mtype = "GET";							//Defines the type of request to make (“POST” or “GET”) 
	protected static final String  multikey = "";						//This parameter makes sense only when the <code>multiselect</code> option is set to <code>true</code>. Defines the key which should be pressed when we make multiselection. The possible values are: <code>shiftKey</code> - the user should press Shift Key, <code>altKey</code> - the user should press Alt Key, and <code>ctrlKey</code> - the user should press Ctrl Key. 
	protected static final Boolean  multiboxonly = false;				//This option works only when the <code>multiselect</code> option is set to <code>true</code>. When <code>multiselect</code> is set to <code>true</code>, clicking anywhere on a row selects that row; when <code>multiboxonly</code> is also set to <code>true</code>, the multiselection is done only when the checkbox is clicked (Yahoo style). Clicking in any other row (suppose the checkbox is not clicked) deselects all rows and selects the current row. 
	protected static final Boolean  multiselect = false;				//If this flag is set to <code>true</code> a multi selection of rows is enabled. A new column at left side containing checkboxes is added. Can be used with any datatype option. 
	protected int  multiselectWidth = 20;								//Determines the width of the checkbox column created when the <code>multiselect</code> option is enabled. 
	protected Boolean  multiSort = false;								//If set to true enables the multisorting. The options work if the datatype is local. In case when the data is obtained from the server the <em>sidx</em> parameter contain the order clause. It is a comma separated String in format field1 asc, field2 desc …, fieldN. Note that the last field does not not have asc or desc. It should be obtained from sord parameter <brstatic finalWhen the option is true the behavior is a s follow. The first click of the header field sort the field depending on the firstsortoption parameter in colModel or sortorder grid parameter. The next click sort it in reverse order. The third click removes the sorting from this field; //protected static final int  page = 1; //		 Set the initial page number when we make the request.This parameter is passed to the url for use by the server routine retrieving the data. 
	protected String  pager = "";										//Defines that we want to use a pager bar to navigate through the records. This must be a valid <abbr title="HyperText Markup Language">HTML</abbr> element; in our example we gave the div the id of “pager”, but any name is acceptable. Note that the navigation layer (the “pager” div) can be positioned anywhere you want, determined by your <abbr title="HyperText Markup Language">HTML</abbr>; in our example we specified that the pager will appear after the  body layer. The valid settings can be (in the context of our example) <code>pager</code>, <code>#pager</code>, <code>jQuery("#pager")</code>. I recommend to use the second one - <code>#pager</code>. See <a href="/jqgridwiki/doku.php?id=wiki:pager" class="wikilink1" title="wiki:pager">Pager</a><del>Currently only one pagebar is possible.</del>
	protected String  pagerpos = "center";								//Determines the position of the pager in the grid. By default the pager element when created is divided in 3 parts (one part for pager, one part for navigator buttons and one part for record information). 
	protected Boolean  pgbuttons = true;								//Determines if the Pager buttons should be shown if pager is available. Also valid only if <code>pager</code> is set correctly. The buttons are placed in the pager bar. 
	protected Boolean  pginput = true;									//Determines if the input box, where the user can change the number of the requested page, should be available. The input box appears in the pager bar. 
	protected static final String pgtext = "See lang file";				//Show information about current page status. The first value is the current loaded page. The second value is the total number of pages. 
	protected static final String prmNames =  "none";					//The default value of this property is: <br/><code>{page:“page”,rows:“rows”, sort:“sidx”, order:“sord”, search:“_search”, nd:“nd”, id:“id”, oper:“oper”, editoper:“edit”, addoper:“add”, deloper:“del”, subgridid:“id”, npage:null, totalrows:“totalrows”}</code> <br/>This customizes names of the fields sent to the server on a POST request. For example, with this setting, you can change the sort order element from <code>sidx</code> to <code>mysort</code> by setting <code>prmNames: {sort: “mysort”}</code>. The String that will be POST-ed to the server will then be <code>myurl.php?page=1&amp;rows=10&amp;mysort=myindex&amp;sord=asc</code>  rather than <code>myurl.php?page=1&amp;rows=10&amp;sidx=myindex&amp;sord=asc</code> <br/>So the value of the column on which to sort upon can be obtained by looking at <code>$POST["mysort"]</code> in PHP. When some parameter is set to null, it will be not sent to the server. For example if we set <code>prmNames: {nd:null}</code> the <code>nd</code> parameter will not be sent to the server.  For <code>npage</code> option see the <code>scroll</code> option. <br/>These options have the following meaning and default values: <br/><code>page</code>: the requested page (default value <code>page</code>) <br/><code>rows</code>: the number of rows requested (default value <code>rows</code>) <br/><code>sort</code>: the sorting column (default value <code>sidx</code>) <br/><code>order</code>: the sort order (default value <code>sord</code>) <br/><code>search</code>: the search indicator (default value <code>_search</code>) <br/><code>nd</code>: the time passed to the request (for <abbr title=\"Internet Explorer\">IE</abbr> browsers not to cache the request) (default value <code>nd</code>) <br/><code>id</code>: the name of the id when POST-ing data in editing modules (default value <code>id</code>) <br/><code>oper</code>: the operation parameter (default value <code>oper</code>) <br/><code>editoper</code>: the name of operation when the data is POST-ed in edit mode (default value <code>edit</code>) <br/><code>addoper</code>: the name of operation when the data is posted in add mode (default value <code>add</code>) <br/><code>deloper</code>: the name of operation when the data is posted in delete mode (default value <code>del</code>) <br/><code>totalrows</code>: the number of the total rows to be obtained from server - see <code>rowTotal</code> (default value <code>totalrows</code>) <br/><code>subgridid</code>: the name passed when we click to load data in the subgrid (default value <code>id</code>) 
	protected static final String postData = "[]";						//This array is appended directly to the url. This is an associative array and can be used this way: <code>{name1:value1…}</code>. See <abbr title="Application Programming Interface">API</abbr> methods for manipulation. 
	protected int  reccount = 0;										//Readonly property. Determines the exact number of rows in the grid. Do not confuse this with <code>records</code> parameter. Although in many cases they may be equal, there are cases where they are not. For example, if you define <code>rowNum</code> to be 15, but the request to the server returns 20 records, the <code>records</code> parameter will be 20, but the <code>reccount</code> parameter will be 15 (the grid you will have 15 records and not 20). 
	protected String  recordpos = "right";								//Determines the position of the record information in the pager. Can be <code>left</code>, <code>center</code>, <code>right</code>. 
	protected int records = 0;											//Readonly property. Gives the number of records returned as a result of a query to the server. 
	protected static final String recordtext = "Se ven {0}-{1} de {2}";	//Text that can be shown in the pager. Also this option is valid if <code>viewrecords</code> option is set to <code>true</code>. This text appears only if the total number of records is greater then zero. In order to show or hide some information the items in {} mean the following: <br/>{0} - the start position of the records depending on page number and number of requested records <br/>{1} - the end position <br/>{2} - total records returned from the server. 
	protected String  resizeclass = "";									//Assigns a class to columns that are resizable so that we can show a resize handle only for ones that are resizable. 
	protected String  rowList = "[]";									//An array to construct a select box element in the pager in which we can change the number of the visible rows. When changed during the execution, this parameter replaces the <code>rowNum</code> parameter that is passed to the url. If the array is empty, this element does not appear in the pager. Typically you can set this like <code>[10,20,30]</code>. If the <code>rowNum</code> parameter is set to 30 then the selected value in the select box is 30. 
	protected Boolean  rownumbers = false;								//If this option is set to <code>true</code>, a new column at left of the grid is added. The purpose of this column is to count the number of available rows, beginning from 1. In this case <code>colModel</code> is extended automatically with new element with the name <code>rn</code>. <strong>Note:</strong> Do not to use the name <code>rn</code> in the <code>colModel</code>. 
	protected static final int  rowNum = 20;							//Sets how many records we want to view in the grid. This parameter is passed to the url for use by the server routine retrieving the data. Note that if you set this parameter to 10 (i.e. retrieve 10 records) and your server return 15 then only 10 records will be loaded. <del>Set this parameter to <strong>-1</strong>  (unlimited) to disable this checking.</del> 
	protected static final int  rowTotal = 0;							//When set this parameter can instruct the server to load the total number of rows needed to work on. Note that <code>rowNum</code> determines the total records displayed in the grid, while <code>rowTotal</code> determines the total number of rows on which we can operate. When this parameter is set, we send an additional parameter to the server named <code>totalrows</code>. You can check for this parameter, and if it is available you can replace the <code>rows</code> parameter with this one. Mostly this parameter can be combined with <code>loadonce</code> parameter set to <code>true</code>.
	protected int  rownumWidth = 25;									//Determines the width of the row number column if <code>rownumbers</code> option is set to <code>true</code>. 
	protected String  savedRow = "[]";									//This is a readonly property and is used in inline and cell editing modules to store the data, before editing the row or cell. See <a href="/jqgridwiki/doku.php?id=wiki:cell_editing" class="wikilink1" title="wiki:cell_editing">Cell Editing</a> and <a href="/jqgridwiki/doku.php?id=wiki:inline_editing" class="wikilink1" title="wiki:inline_editing">Inline Editing</a>. 
	protected static final String searchdata = "[]";					//<del>This property contain the searched data in pair name:value.</del>
	protected int  scroll = -1;											//Creates dynamic scrolling grids. When enabled, the pager elements are disabled and we can use the vertical scrollbar to load data. When set to <code>true</code> the grid will always hold all the items from the start through to the latest point ever visited. <br/>When <code>scroll</code> is set to an int value  (example 1), the grid will just hold the visible lines. This allow us to load the data in portions whitout caring about memory leaks. In addition to this we have an optional extension to the server protocol: <code>npage</code> (see <code>prmNames</code> array). If you set the npage option in <code>prmNames</code>, then the grid will sometimes request more than one page at a time; if not, it will just perform multiple GET requests. <br/>Note that this option is not compatible when a grid parameter <strong>height</strong> is set to <code>auto</code> or <code>100%</code>. 
	protected int  scrollOffset = 18;									//Determines the width of the vertical scrollbar. Since different browsers interpret this width differently (and it is difficult to calculate it in all browsers) this can be changed. 
	protected static final int  scrollTimeout = 200;					//This controls the timeout handler when <code>scroll</code> is set to 1. 
	protected static final Boolean  scrollrows = false;					//When enabled, selecting a row with <code>setSelection</code> scrolls the grid so that the selected row is visible. This is especially useful when we have a verticall scrolling grid and we use form editing with navigation buttons (next or previous row). On navigating to a hidden row, the grid scrolls so that the selected row becomes visible. 
	protected String  selarrrow = "[]";									//This options is readonly. Gives the currently selected rows when <code>multiselect</code> is set to <code>true</code>. This is a one-dimensional array and the values in the array correspond to the selected id"s in the grid. 
	protected String  selrow = null;									//This option is readonly. It contains the id of the last selected row. If you sort or use paging, this options is set to null. 
	protected int  shrinkToFit = 0;										//This option, if set, defines how the the width of the columns of the grid should be re-calculated, taking into consideration the width of the grid. If this value is <code>true</code>, and the width of the columns is also set, then every column is scaled in proportion to its width. For example, if we define two columns with widths 80 and 120 pixels, but want the grid to have a width of 300 pixels, then the columns will stretch to fit the entire grid, and the extra width assigned to them will depend on the width of the columns themselves and the extra width available. The re-calculation is done as follows: the first column gets the width (300(new width)/200(sum of all widths))*80(first column width) = 120 pixels, and the second column gets the width (300(new width)/200(sum of all widths))*120(second column width) = 180 pixels. Now the widths of the columns sum up to 300 pixels, which is the width of the grid. If the value is false and the value in <code>width</code> option is set, then no re-sizing happens whatsoever. So in this example, if <code>shrinkToFit</code> is set to false, column one will have a width of 80 pixels, column two will have a width of 120 pixels and the grid will retain the width of 300 pixels. If the value of shrinkToFit is an int, the width is calculated according to it. <img src="/jqgridwiki/lib/images/smileys/fixme.gif" class="icon" alt="FIXME" /> - The effect of using an int can be elaborated. 
	protected Boolean  sortable = false;								//When set to <code>true</code>, this option allows reordering columns by dragging and dropping them with the mouse. Since this option uses the jQuery UI sortable widget, be sure to load this widget and its related files in the <abbr title="HyperText Markup Language">HTML</abbr> head tag of the page. Also, be sure to select the jQuery UI Addons option under the jQuery UI Addon Methods section while downloading jqGrid if you want to use this facility. <strong>Note:</strong> The <code>colModel</code> object also has a property called <code>sortable</code>, which specifies if the grid data can be sorted on a particular column or not. 
	protected static final String  sortname = "";						//The column according to which the data is to be sorted when it is initially loaded from the server (note that you will have to use datatypes "xml" or json to load remote data). This parameter is appended to the url. If this value is set and the index (name) matches the name from colModel, then an icon indicating that the grid is sorted according to this column is added to the column header. This icon also indicates the sorting order - descending or ascending (see the parameter <code>sortorder</code>). Also see <code>prmNames</code>. 
	protected static final String  sortorder = "asc";					//The initial sorting order (ascending or descending) when we fetch data from the server using datatypes "xml" or json. This parameter is appended to the url - see <code>prnNames</code>. The two allowed values are - <code>asc</code> or <code>desc</code>.
	protected Boolean subGrid = false;									//If set to <code>true</code> this enables using a sub-grid. If the <code>subGrid</code> option is enabled, an additional column at left side is added to the basic grid. This column contains a "plus" image which indicates that the user can click on it to expand the row. By default all rows are collapsed. See <a href="/jqgridwiki/doku.php?id=wiki:subgrid" class="wikilink1" title="wiki:subgrid">Subgrid</a>
	protected static final String subGridOptions = "{}";				//A set of additional options for the subgrid. For more information and default values see <a href="/jqgridwiki/doku.php?id=wiki:subgrid" class="wikilink1" title="wiki:subgrid">Subgrid</a>. 
	protected String  subGridModel = "[]";								//This property, which describes the model of the subgrid, has an effect only if the subGrid property is set to <code>true</code>. It is an array in which we describe the column model for the subgrid data. For more information see <a href="/jqgridwiki/doku.php?id=wiki:subgrid" class="wikilink1" title="wiki:subgrid">Subgrid</a>.
	protected static final String  subGridType = null;					//This option allows loading a subgrid as a service. If not set, the <code>datatype</code> parameter of the parent grid is used.
	protected static final String  subGridUrl = "";						//This option has effect only if the <code>subGrid</code> option is set to <code>true</code>. This option points to the url from which we get the data for the subgrid. jqGrid adds the id of the row to this url as parameter. If there is a need to pass additional parameters, use the <code>params</code> option in <code>subGridModel</code>. See <a href="/jqgridwiki/doku.php?id=wiki:subgrid" class="wikilink1" title="wiki:subgrid">Subgrid</a>
	protected int  subGridWidth = 20;									//Defines the width of the sub-grid column if <code>subgrid</code> is enabled.
	protected String  toolbar = "[false, \"\"]";						//This option defines the toolbar of the grid. This is an array with two elements in which the first element"s value enables the toolbar and the second defines the position relative to the body layer (table data). Possible values are <code>top</code>, <code>bottom</code>, and <code>both</code>. When we set it like <code>toolbar: [true,“both”]</code> two toolbars are created – one on the top of table data and the other at the bottom of the table data. When we have two toolbars, then we create two elements (div). The id of the top bar is constructed by concatenating the String “t_” and the id of the grid, like <code>“t_” + id_of_the_grid</code> and the id of the bottom toolbar is constructed by concatenating the String “tb_” and the id of the grid, like <code>“tb_” + id_of_the grid</code>. In the case where only one toolbar is created, we have the id as <code>“t_” + id_of_the_grid</code>, independent of where this toolbar is located (top or bottom)
	protected Boolean  toppager = false;								//When enabled this option places a pager element at top of the grid, below the caption (if available). If another pager is defined, both can coexist and are kept in sync. The id of the newly created pager is the combination <code>grid_id + “_toppager”</code>. 
	protected int  totaltime = 0;										//Readonly parameter. It gives the loading time of the records - currently available only when we load "xml" or json data. The measurement begins when the request is complete and ends when the last row is added. 
	protected String  treedatatype = null;								//Gives the initial datatype (see <code>datatype</code> option). Usually this should not be changed. During the reading process this option is equal to the datatype option. 
	protected Boolean  treeGrid = false;								//Enables (disables) the tree grid format. For more details see <a href="/jqgridwiki/doku.php?id=wiki:treegrid" class="wikilink1" title="wiki:treegrid"> Tree Grid</a>
	protected String  treeGridModel = "nested";							//Deteremines the method used for the <code>treeGrid</code>. The value can be either <code>nested</code> or <code>adjacency</code>. See <a href="/jqgridwiki/doku.php?id=wiki:treegrid" class="wikilink1" title="wiki:treegrid">Tree Grid</a>
	protected String  treeIcons = "";									//This array sets the icons used in the tree grid. The icons should be a valid names from UI theme roller images. The default values are: <code>{plus:"ui-icon-triangle-1-e",minus:"ui-icon-triangle-1-s",leaf:"ui-icon-radio-off"}</code> 
	protected String  treeReader = "";									//Extends the <code>colModel</code> defined in the basic grid. The fields described here are appended to end of the <code>colModel</code> array and are hidden. This means that the data returned from the server should have values for these fields. For a full description of all valid values see <a href="/jqgridwiki/doku.php?id=wiki:treegrid" class="wikilink1" title="wiki:treegrid"> Tree Grid</a>.
	protected int  tree_root_level = 0;									//Defines the level where the root element begins when treeGrid is enabled. 
	protected static final String  url = null;							//The url of the file that returns the data needed to populate the grid.  May be set to <code>clientArray</code> to manualy post data to server; see <a href="/jqgridwiki/doku.php?id=wiki:inline_editing_s_clientarray" class="wikilink2" title="wiki:inline_editing_s_clientarray" rel="nofollow">Inline Editing</a>. 
	protected String  userData = "[]";									//This array contains custom information from the request. Can be used at any time. 
	protected static final Boolean  userDataOnFooter = false;			//When set to <code>true</code> we directly place the user data array <code>userData</code> in the footer. The rules are as follows: If the <code>userData</code> array contains a name which matches any name defined in <code>colModel</code>, then the value is placed in that column. If there are no such values nothing is placed. Note that if this option is used we use the current formatter options (if available) for that column.
	protected Boolean  viewrecords = false;								//If <code>true</code>, jqGrid displays the beginning and ending record number in the grid, out of the total number of records in the query.  This information is shown in the pager bar (bottom right by default)in this format: “View X to Y out of Z”.  If this value is <code>true</code>, there are other parameters that can be adjusted, including <code>emptyrecords</code> and <code>recordtext</code>. 
	protected String  viewsortcols = "[false,\"vertical\",true]";		//The purpose of this parameter is to define a different look and behavior for the sorting icons (up/down arrows) that appear in the column headers. This parameter is an array with the following default options <code>viewsortcols : [false,"vertical",true]</code>. The first parameter determines if sorting icons should be visible on all the columns that have the sortable property set to <code>true</code>. Setting this value to <code>true</code> could be useful if you want to indicate to the user that (s)he can sort on that particular column. The default of false sets the icon to be visible only on the column on which that data has been last sorted. Setting this parameter to <code>true</code> causes all icons in all sortable columns to be visible.<br/>The second parameter determines how icons should be placed - <code>vertical</code> means that the sorting arrows are one under the other. "horizontal" means that the arrows should be next to one another. <br/>The third parameter determines the click functionality. If set to <code>true</code> the data is sorted if the user clicks anywhere in the column"s header, not only the icons. If set to false the data is sorted only when the sorting icons in the headers are clicked. <br/><strong>Important:</strong> If you are setting the third element to false, make sure that you set the first element to <code>true</code>; if you don"t, the icons will not be visible and the user will not know where to click to be able to sort since clicking just anywhere in the header will not guarantee a sort. 
	protected String  width = "0";										//If this option is not set, the width of the grid is the sum of the widths of the columns defined in the colModel (in pixels). If this option is set, the initial width of each column is set according to the value of the <code>shrinkToFit</code> option. 
	protected String  xmlReader = "[]";									//An array which describes the structure of the expected "xml" data. For a full description refer to <a href="/jqgridwiki/doku.php?id=wiki:retrieving_data#"xml"_data" class="wikilink1" title="wiki:retrieving_data"> Retrieving Data in "xml" Format</a>. 



}
