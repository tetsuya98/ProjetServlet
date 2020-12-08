package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import hw.GestionDeStockRemote;
import hw.Produit;

/**
 * Servlet implementation class GestionDeStockServlet
 */
@WebServlet("/getProduits")
public class GestionDeStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionDeStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	GestionDeStockRemote gestionStock=null; 
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	InitialContext context=null;		
    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
    		context = new InitialContext(jndiProperties);
    		// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI
    		gestionStock = (GestionDeStockRemote)context.lookup("java:global/ProjetJEEComplet/ProjetEJB/GestionDeStock!hw.GestionDeStockRemote");
    	} catch (NamingException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	PrintWriter out=null;
    	response.setContentType("text/html");
    	out=response.getWriter();
    	out.println("<html>");
    	out.println("<head><title>Gestion de stock</title></head>");
    	out.println("<body>");
    	out.println("Les produits en stock");
    	out.println("<table border=2>");
    	out.println("<thead>");
    	out.println("<tr>");
    	out.println("<th>ID</th>");
    	out.println("<th>Libellé</th>");
    	out.println("<th>Quantité</th>");
    	out.println("</tr>");
    	out.println("</thead>");
    	// Appel de l'ejb façade
    	List<Produit> produits = gestionStock.listerTousLesProduits();
    	// Parcours des prosuits et affichage dans le tableau
    	for (Produit produit:produits)
    	{
    		out.println("<tr>");
    		out.println("<td>");
    		out.println(produit.getId());
    		out.println("</td>");
    		out.println("<td>");
    		out.println(produit.getLibelle());
    		out.println("</td>");
    		out.println("<td>");
    		out.println(produit.getQuantiteEnStock());
    		out.println("</td>");
    		out.println("</tr>");
    	}
    	out.println("</table>");
    	out.println("</body>");
    	out.println("</html>");
    }


}
