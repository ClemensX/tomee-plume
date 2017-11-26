package org.superbiz.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.superbiz.eclipselink.Movie;
import org.superbiz.eclipselink.Movies;

/**
 * Servlet implementation class Hello2
 */
@WebServlet("/Hello2")
public class Hello2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private Movies movies;
	
	@PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public Hello2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		response.getWriter().append("\nInjected EntityManager: " + entityManager);

		response.getWriter().append("\nCreate JPA Entity... ");
		Movie movie = new Movie("Quentin Tarantino", "Reservoir Dogs", 1992);
		response.getWriter().append("ok\n");

		response.getWriter().append("Persist... ");
		try {
			// entityManager.getTransaction().begin();
			entityManager.persist(movie);
			// entityManager.getTransaction().commit();
			response.getWriter().append("ok\n");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
			response.getWriter().append("failed!!!\n");
		}
		//Movies movies = (Movies) context.lookup("java:global/tomeetest/Movies");

		response.getWriter().append("\nInjected EJB: " + movies);
		response.getWriter().append("Persist via ejb... ");
		try {
			movies.addMovie(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
			response.getWriter().append("ok\n");
			List<Movie> movielist = movies.getMovies();
			response.getWriter().append("movies in db: " + movielist.size() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append("failed!!!\n");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
