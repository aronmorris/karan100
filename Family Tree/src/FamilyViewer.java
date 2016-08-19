
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * BORROWED AND MODIFIED CODE FROM PUBLIC JGRAPHT EXAMPLE BY:
 * 
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
public class FamilyViewer extends JApplet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3670735964267916607L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 1000, 1000 );

    // 
    private JGraphModelAdapter<String, Relationship> m_jgAdapter;
    
    private FamilyTree family;
    
    private int xOffset, yOffset;
    
    //moving out of init for wider scope access
    ListenableGraph<String, Relationship> g;
    
    /**
     * @see java.applet.Applet#init().
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(  ) {
        // create a JGraphT graph
        g = new ListenableDirectedGraph( DefaultEdge.class );

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter<String, Relationship>( g );

        JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );
        /*
        // add some sample data (graph manipulated via JGraphT)
        g.addVertex( "v1" );
        g.addVertex( "v2" );
        g.addVertex( "v3" );
        g.addVertex( "v4" );

        g.addEdge( "v1", "v2" );
        g.addEdge( "v2", "v3" );
        g.addEdge( "v3", "v1" );
        g.addEdge( "v4", "v3" );
         */
        
        /*
        // position vertices nicely within JGraph component
        positionVertexAt( "v1", 130, 40 );
        positionVertexAt( "v2", 60, 200 );
        positionVertexAt( "v3", 310, 230 );
        positionVertexAt( "v4", 380, 70 );
		
        // that's all there is to it!...
        */
     
        family = this.declarePeople("Larry", "Sergei", "Jesse", "James", "Kamehameha", "Bruce");
        
        defineRelation("Larry", "Jesse", RType.SIBLING);
        defineRelation("Larry", "James", RType.CHILD);
        defineRelation("Bruce", "Sergei", RType.PARTNER);
 
    }
    
    //LOOK HERE FIRST DOPE
    //Put the names of the people into the UI after declaring every person and relation, fetching the relations from the Person object
    //and ensuring there's no weird repeats
    //It's a directed graph so presumably the first arg in addEdge(a, b) is the initial point
    //Sibling and partner relations will look onesided but w/e unless the property is supported
    //TODO test two-way direction?


    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }

    //removed x and y in favour of offset global args
    private void positionVertexAt( Object vertex ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        AttributeMap             attr = cell.getAttributes(  );
        //Rectangle        b    = GraphConstants.getBounds( attr );
        
        GraphConstants.setBounds( attr, new Rectangle( xOffset, yOffset, 100, 30 ) ); //it's magic but the code commented out above was broken and this does the same thing

        
        xOffset += 150;
        if (xOffset % 600 == 0) {
        	xOffset = 0;
        	yOffset += 100;
        }
        
       
        
        Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
    }
    
    public FamilyTree declarePeople(String... people) {
    	FamilyTree ft = new FamilyTree();
    	
    	for (String p : people) {
    		
    		Person prsn = new Person(p, new Date());
    		
    		g.addVertex(prsn.getName());
    		
    		ft.addPerson(prsn);
    		
    		positionVertexAt(prsn.getName());
    		
    	}
    	
    	return ft;
    	
    }
    
    public void defineRelation(String A, String B, RType relation) {
    	family.addRelation(A, B, relation);
    	g.addEdge(A, B);
    }
    
}