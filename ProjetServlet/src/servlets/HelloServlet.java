package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out=null;
    	response.setContentType("text/html");
    	out=response.getWriter();
    	out.println("<html>");
    	out.println("<head><title>Test de servlet</title></head>");
    	out.println("<body>");
    	out.println("Affichage de contenu dans une page web");
    	out.println("</body>");
    	out.println("</html>");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomPersonne;
		try {
			// Récupération du flux d'entrée envoyé par l'applet
			ObjectInputStream entree=new ObjectInputStream(request.getInputStream());
			// Récupération de l'objet envoyé par le client
			nomPersonne=(String)entree.readObject();
			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			// Travail à réaliser sur les données en entrée
			String chaineBienvenue = "Bienvenue "+nomPersonne+" je pressens pour vous une belle journée";
			// Envoi du résultat au client
			sortie.writeObject(chaineBienvenue);
			} catch (Exception ex) {
			System.out.println("Erreur d'exécution de la requête SQL : "+ex);
		}
	}

}
