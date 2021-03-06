package org.javaforever.infinity.include;

import org.javaforever.infinity.domain.Include;
import org.javaforever.infinity.utils.StringUtil;
import org.javaforever.infinity.verb.ListAllByPage;

public class HomePageNav extends Include{	
	public HomePageNav(){
		super();
		this.fileName = "homepagenav.jsp";
		this.packageToken = "";
	}

	@Override
	public String generateIncludeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- Common Navigation Panel for our site -->\n");
		sb.append("<li id=\"submenu\">\n");
		sb.append("\t<h2>Select an option</h2>\n");
		sb.append("\t<ul>\n");
		sb.append("\t\t<li><a href=\""+"jsp/index.jsp\">Normal JSP interface</a></li>\n"); 
		sb.append("\t\t<li><a href=\""+"jsonjsp/index.jsp\">Josn UI interface</a></li>\n"); 
		sb.append("\t</ul>\n");
		sb.append("</li>\n");
		return sb.toString();
	}
}
