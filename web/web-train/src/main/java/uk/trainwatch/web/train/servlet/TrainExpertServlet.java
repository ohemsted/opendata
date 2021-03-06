/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.train.servlet;

import javax.servlet.annotation.WebServlet;
import uk.trainwatch.web.servlet.ApplicationRequest;
import uk.trainwatch.web.train.Train;

/**
 * Expert view of a train service
 * <p>
 * @author peter
 */
@WebServlet(name = "TrainExpertServlet", urlPatterns = "/train/expert/*")
public class TrainExpertServlet
        extends AbstractTrainServlet
{

    @Override
    protected void show( ApplicationRequest request, String rid, Train train )
    {
        getDetails( request, rid, train );
        getDelay( request, rid, train );
        getStatus( request, rid, train );
        request.renderTile( "train.expert" );
    }

}
