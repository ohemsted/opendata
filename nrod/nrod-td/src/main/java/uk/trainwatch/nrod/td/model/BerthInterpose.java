/*
 * Copyright 2014 Peter T Mount.
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
package uk.trainwatch.nrod.td.model;

/**
 * The CC message is an 'interpose' message.
 * <p>
 * This inserts the description in to the 'to' berth, overwriting any description in the 'to' berth.
 * <p>
 * @author Peter T Mount
 */
public class BerthInterpose
        extends TDMessage
{

    private String to;
    private String descr;

    public BerthInterpose()
    {
        super( TDMessageType.CC );
    }

    public String getTo()
    {
        return to;
    }

    public void setTo( String to )
    {
        this.to = to;
    }

    public String getDescr()
    {
        return descr;
    }

    public void setDescr( String descr )
    {
        this.descr = descr;
    }

    @Override
    public void accept( TDVisitor v )
    {
        v.visit( this );
    }

}
