package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

import java.util.List;

public class GetTimingModel {


    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }



    public String route_id;
    public String bus_id;
    public List<Timing> timings;

    public List<Timing> getTimings() {
        return timings;
    }

    public void setTimings(List<Timing> timings) {
        this.timings = timings;
    }

    public class Timing {


        public String bus_ids;

        public String getBus_ids() {
            return bus_ids;
        }

        public void setBus_ids(String bus_ids) {
            this.bus_ids = bus_ids;
        }

        public String getBus_shedule_id() {
            return bus_shedule_id;
        }

        public void setBus_shedule_id(String bus_shedule_id) {
            this.bus_shedule_id = bus_shedule_id;
        }

        public String getPick_up() {
            return pick_up;
        }

        public void setPick_up(String pick_up) {
            this.pick_up = pick_up;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getStop_id() {
            return stop_id;
        }

        public void setStop_id(String stop_id) {
            this.stop_id = stop_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String bus_shedule_id;
        private String pick_up;
        private String updatedAt;
        private String stop_id;
        private int id;
        private String bus_id;

        public String getBus_id() {
            return bus_id;
        }

        public void setBus_id(String bus_id) {
            this.bus_id = bus_id;
        }
    }
}

