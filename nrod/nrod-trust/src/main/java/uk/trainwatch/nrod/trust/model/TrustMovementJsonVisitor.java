/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.nrod.trust.model;

import java.util.Date;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Peter T Mount
 */
public class TrustMovementJsonVisitor
        implements TrustMovementVisitor
{

    private JsonObjectBuilder b;

    public JsonObjectBuilder getJsonObjectBuilder()
    {
        return b;
    }

    private void add( String k, Date v )
    {
        if( v == null )
        {
            b.addNull( k );
        }
        else
        {
            b.add( k, v.getTime() );
        }
    }

    private void add( String k, String v )
    {
        if( v == null )
        {
            b.addNull( k );
        }
        else
        {
            b.add( k, v );
        }
    }

    /**
     * Ensures we have the common fields in place
     * <p>
     * @param t
     */
    private void init( TrustMovement t )
    {
        b = Json.createObjectBuilder().
                add( "msg_type", t.getMsg_type().
                     getType() ).
                add( "toc_id", t.getToc_id() );
        add( "train_id", t.getTrain_id() );
    }

    @Override
    public void visit( TrainActivation t )
    {
        init( t );
        
        add( "schedule_source", t.getSchedule_source() );
        add( "train_file_address", t.getTrain_file_address() );
        add( "schedule_end_date", t.getSchedule_end_date() );
        add( "tp_origin_timestamp", t.getTp_origin_timestamp() );

        b.add( "creation_timestamp", t.getCreation_timestamp() ).
                add( "tp_origin_stanox", t.getTp_origin_stanox() ).
                add( "origin_dep_timestamp", t.getOrigin_dep_timestamp() );

        add( "train_service_code", t.getTrain_service_code() );
        add( "d1266_record_number", t.getD1266_record_number() );
        add( "train_call_type", t.getTrain_call_type() );
        add( "train_uid", t.getTrain_uid() );
        add( "train_call_mode", t.getTrain_call_mode() );
        add( "schedule_type", t.getSchedule_type() );
        b.add( "sched_origin_stanox", t.getSched_origin_stanox() );
        add( "schedule_wtt_id", t.getSchedule_wtt_id() );
        add( "schedule_start_date", t.getSchedule_start_date() );
    }

    @Override
    public void visit( TrainCancellation c )
    {
        init( c );

        b.add( "orig_loc_stanox", c.getOrig_loc_stanox() ).
                add( "orig_loc_timestamp", c.getOrig_loc_timestamp() ).
                add( "dep_timestamp", c.getDep_timestamp() ).
                add( "division_code", c.getDivision_code() ).
                add( "loc_stanox", c.getLoc_stanox() ).
                add( "canx_timestamp", c.getCanx_timestamp() );

        add( "canx_reason_code", c.getCanx_reason_code() );
        add( "canx_type", c.getCanx_type() );
        add( "train_service_code", c.getTrain_service_code() );
        add( "train_file_address", c.getTrain_file_address() );

        // plannedCancellation & reactionaryCancellation are based on this
        add( "original_data_source", c.getOriginal_data_source() );
    }

    @Override
    public void visit( TrainMovement t )
    {
        init( t );

        add( "event_type", t.getEvent_type() );
        b.add( "gbtt_timestamp", t.getGbtt_timestamp() ).
                add( "original_loc_stanox", t.getOriginal_loc_stanox() ).
                add( "planned_timestamp", t.getPlanned_timestamp() ).
                add( "timetable_variation", t.getTimetable_variation() ).
                add( "original_loc_timestamp", t.getOriginal_loc_timestamp() );
        add( "current_train_id", t.getCurrent_train_id() );
        b.add( "delay_monitoring_point", t.isDelay_monitoring_point() ).
                add( "reporting_stanox", t.getReporting_stanox() ).
                add( "actual_timestamp", t.getActual_timestamp() ).
                add( "correction_ind", t.isCorrection_ind() );
        add( "event_source", t.getEvent_source() );
        add( "train_file_address", t.getTrain_file_address() );
        add( "platform", t.getPlatform() );
        add( "division_code", t.getDivision_code() );
        b.add( "train_terminated", t.isTrain_terminated() );
        b.add( "offroute_ind", t.isOffroute_ind() );
        add( "variation_status", t.getVariation_status() );
        add( "train_service_code", t.getTrain_service_code() );
        b.
                add( "loc_stanox", t.getLoc_stanox() ).
                add( "auto_expected", t.isAuto_expected() ).
                add( "direction_ind", t.getDirection_ind() );
        add( "route", t.getRoute() );
        add( "planned_event_type", t.getPlanned_event_type() );
        b.add( "next_report_stanox", t.getNext_report_stanox() );
        add( "line_ind", t.getLine_ind() );
    }

    private void initAdjustment( TrustAdjustment t )
    {
        init( t );
        
        add( "current_train_id", t.getCurrent_train_id() );
        b.add( "original_loc_timestamp", t.getOriginal_loc_timestamp() ).
                add( "dep_timestamp", t.getDep_timestamp() ).
                add( "loc_stanox", t.getLoc_stanox() ).
                add( "original_loc_stanox", t.getOriginal_loc_stanox() );
        add( "train_file_address", t.getTrain_file_address() );
        add( "train_service_code", t.getTrain_service_code() );
    }

    @Override
    public void visit( TrainReinstatement t )
    {
        initAdjustment( t );

        b.add( "reinstatement_timestamp", t.getReinstatement_timestamp() ).
                add( "division_code_id", t.getDivision_code_id() );
    }

    @Override
    public void visit( ChangeOfOrigin r )
    {
        initAdjustment( r );
        
        add( "reason_code", r.getReason_code() );
        b.add( "coo_timestamp", r.getCoo_timestamp() ).
                add( "division_code", r.getDivision_code() );
    }

    @Override
    public void visit( ChangeOfIdentity r )
    {
        // Note: tocId will always be 0 as this is for freight
        init( r );
        
        add( "current_train_id", r.getCurrent_train_id() );
        add( "revised_train_id", r.getRevised_train_id() );
        add( "train_file_address", r.getTrain_file_address() );
        add( "train_service_code", r.getTrain_service_code() );
        b.add( "event_timestamp", r.getEvent_timestamp() );
    }
}
