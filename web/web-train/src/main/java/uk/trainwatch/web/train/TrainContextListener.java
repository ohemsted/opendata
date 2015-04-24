/*
 * Copyright 2014 peter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.trainwatch.web.train;

import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import uk.trainwatch.nre.darwin.forecast.ForecastManager;
import uk.trainwatch.nre.darwin.reference.DarwinReferenceManager;
import uk.trainwatch.util.sql.DBContextListener;

/**
 *
 * @author peter
 */
@WebListener
public class TrainContextListener
        extends DBContextListener
{

    @Override
    protected void init( ServletContextEvent sce )
            throws SQLException
    {
        //DataSource dataSource = getRailDataSource();

        DataSource dataSource;
        try {
            dataSource = InitialContext.doLookup( "java:/comp/env/jdbc/railDev" );
            log.log( Level.SEVERE, "Forecasts Running against DEV DB" );
        }
        catch( NamingException ex ) {
            dataSource = getRailDataSource();
            log.log( Level.SEVERE, "Forecasts Running against LIVE DB" );
        }

        ForecastManager.INSTANCE.setDataSource( dataSource );
        DarwinReferenceManager.INSTANCE.setDataSource( dataSource );
    }

}