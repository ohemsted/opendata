/*
 * Copyright 2015 peter.
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
package uk.trainwatch.nrod.feed;

import uk.trainwatch.nrod.feed.trust.TrustFeed;
import uk.trainwatch.nrod.feed.rtppm.RtppmFeed;
import uk.trainwatch.nrod.feed.td.TdFeed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * WebListener that starts the various feeds on startup
 * <p>
 * @author peter
 */
@WebListener
@ApplicationScoped
public class NetworkRailFeed
        implements ServletContextListener
{

    @Inject
    private RtppmFeed rtppmFeed;

    @Inject
    private TdFeed tdFeed;

    @Inject
    private TrustFeed trustFeed;

    @Override
    public void contextInitialized( ServletContextEvent sce )
    {
        rtppmFeed.accept( null );
        tdFeed.accept( null );
        trustFeed.accept( null );
    }

    @Override

    public void contextDestroyed( ServletContextEvent sce )
    {
    }
}
