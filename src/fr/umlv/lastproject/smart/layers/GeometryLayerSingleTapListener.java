package fr.umlv.lastproject.smart.layers;

/**
 * This class is used to send event when the layer is taped
 * @author thibault
 *
 */
public interface GeometryLayerSingleTapListener {
	
	/**
	 * 
	 * @param p the point where the layer has been taped
	 */
	public void actionPerformed(PointGeometry p) ;

}
