package pl.spdb.app.algorithm.helpers;

import com.google.gson.Gson;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.model.graph.Graph;
import pl.spdb.app.model.graph.PointOfInterest;
import pl.spdb.app.model.graph.Road;
import pl.spdb.app.model.matrix.Element;
import pl.spdb.app.model.matrix.Rows;
import pl.spdb.app.model.poi.Venue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCreator {

    private final DistanceMatrixService distanceMatrixService;

    public GraphCreator(DistanceMatrixService distanceMatrixService) {
        this.distanceMatrixService = distanceMatrixService;
    }

    public Graph create(List<Venue> venues, boolean mocked) {
        Graph graph = new Graph();
        for (Venue venue : venues) {
            graph.addPointOfInterest(venue);
        }

        List<String> distances = Arrays.asList(get0(), get1(), get2(), get3(), get4(), get5(), get6(), get7(), get8(), get9(), get10(), get11(), get12(), get13(), get14(), get15(), get16(), get17(), get18(), get19());

        for (int i = 0; i < venues.size() - 1; ++i) {
            List<String> origins = new ArrayList<>();
            origins.add(venues.get(i).getLatitudeAndLongitude()); //set only one origin

            List<String> destinations = new ArrayList<>();
            for (int y = i+1; y < venues.size(); ++y) {
                destinations.add(venues.get(y).getLatitudeAndLongitude());
            }
            List<Element> elements;
            if (!mocked)
                elements = distanceMatrixService.getDistanceMatrix(origins, destinations).getRows().get(0).getElements(); //TODO change to only this when algorithm ready
            else
                elements = new Gson().fromJson(distances.get(i), Rows.class).getRows().get(0).getElements(); //TODO this delete

            PointOfInterest pointOfInterest = graph.getPointOfInterest(venues.get(i).getId());
            for (int y = 0; y < elements.size(); ++y) {
                String destination = venues.get(i+y+1).getId();
                Road road = new Road(elements.get(y));
                pointOfInterest.addRoad(destination, road);
                PointOfInterest destPOI = graph.getPointOfInterest(destination);
                destPOI.addRoad(pointOfInterest.getVenue().getId(), road);
            }
        }

        return graph;
    }

    private String get19() {
        return "{\n" +
                "   \"destination_addresses\" : [ \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\" ],\n" +
                "   \"origin_addresses\" : [ \"Żurawia 22, 00-515 Warszawa, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"138 km\",\n" +
                "                  \"value\" : 137666\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 29 mins\",\n" +
                "                  \"value\" : 8932\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get18() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Ogrodowa 19, 91-071 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"135 km\",\n" +
                "                  \"value\" : 135127\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 26 mins\",\n" +
                "                  \"value\" : 8777\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.6 km\",\n" +
                "                  \"value\" : 3569\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"12 mins\",\n" +
                "                  \"value\" : 713\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get17() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Park, 90-001 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.4 km\",\n" +
                "                  \"value\" : 4435\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"12 mins\",\n" +
                "                  \"value\" : 716\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"136 km\",\n" +
                "                  \"value\" : 135749\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 27 mins\",\n" +
                "                  \"value\" : 8826\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.1 km\",\n" +
                "                  \"value\" : 2073\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"4 mins\",\n" +
                "                  \"value\" : 243\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get16() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Żelazowa Wola 14, 96-503, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"92.4 km\",\n" +
                "                  \"value\" : 92385\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 32 mins\",\n" +
                "                  \"value\" : 5532\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"90.0 km\",\n" +
                "                  \"value\" : 89979\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 29 mins\",\n" +
                "                  \"value\" : 5336\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"52.8 km\",\n" +
                "                  \"value\" : 52780\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 6 mins\",\n" +
                "                  \"value\" : 3943\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"92.1 km\",\n" +
                "                  \"value\" : 92085\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 29 mins\",\n" +
                "                  \"value\" : 5353\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get15() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Nadarzyńska 4, 05-807 Żółwin, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"41.1 km\",\n" +
                "                  \"value\" : 41110\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"46 mins\",\n" +
                "                  \"value\" : 2755\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"110 km\",\n" +
                "                  \"value\" : 110162\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 3 mins\",\n" +
                "                  \"value\" : 7384\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"108 km\",\n" +
                "                  \"value\" : 107757\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 0 mins\",\n" +
                "                  \"value\" : 7188\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"29.0 km\",\n" +
                "                  \"value\" : 28956\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"44 mins\",\n" +
                "                  \"value\" : 2612\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"109 km\",\n" +
                "                  \"value\" : 109206\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 59 mins\",\n" +
                "                  \"value\" : 7118\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get14() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Skrzydlata 64, 91-503 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"110 km\",\n" +
                "                  \"value\" : 109914\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 53 mins\",\n" +
                "                  \"value\" : 6767\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"83.2 km\",\n" +
                "                  \"value\" : 83217\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 19 mins\",\n" +
                "                  \"value\" : 4760\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"10.6 km\",\n" +
                "                  \"value\" : 10608\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"20 mins\",\n" +
                "                  \"value\" : 1218\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"5.8 km\",\n" +
                "                  \"value\" : 5793\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"13 mins\",\n" +
                "                  \"value\" : 782\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"132 km\",\n" +
                "                  \"value\" : 132227\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 19 mins\",\n" +
                "                  \"value\" : 8353\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"10.3 km\",\n" +
                "                  \"value\" : 10308\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"17 mins\",\n" +
                "                  \"value\" : 1039\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get13() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.5 km\",\n" +
                "                  \"value\" : 7530\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"18 mins\",\n" +
                "                  \"value\" : 1077\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"109 km\",\n" +
                "                  \"value\" : 108939\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 59 mins\",\n" +
                "                  \"value\" : 7135\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"86.5 km\",\n" +
                "                  \"value\" : 86456\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 29 mins\",\n" +
                "                  \"value\" : 5354\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.3 km\",\n" +
                "                  \"value\" : 4267\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"13 mins\",\n" +
                "                  \"value\" : 765\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.2 km\",\n" +
                "                  \"value\" : 3242\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"10 mins\",\n" +
                "                  \"value\" : 615\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"135 km\",\n" +
                "                  \"value\" : 135466\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 29 mins\",\n" +
                "                  \"value\" : 8948\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.7 km\",\n" +
                "                  \"value\" : 3657\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"10 mins\",\n" +
                "                  \"value\" : 584\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get12() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"102 km\",\n" +
                "                  \"value\" : 102237\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 49 mins\",\n" +
                "                  \"value\" : 6532\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"101 km\",\n" +
                "                  \"value\" : 101245\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 44 mins\",\n" +
                "                  \"value\" : 6267\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.5 km\",\n" +
                "                  \"value\" : 7454\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"12 mins\",\n" +
                "                  \"value\" : 742\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"36.3 km\",\n" +
                "                  \"value\" : 36260\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"38 mins\",\n" +
                "                  \"value\" : 2290\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"104 km\",\n" +
                "                  \"value\" : 103746\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 52 mins\",\n" +
                "                  \"value\" : 6735\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"101 km\",\n" +
                "                  \"value\" : 101341\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 49 mins\",\n" +
                "                  \"value\" : 6538\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"32.8 km\",\n" +
                "                  \"value\" : 32769\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"49 mins\",\n" +
                "                  \"value\" : 2937\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 102790\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 48 mins\",\n" +
                "                  \"value\" : 6469\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get11() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"0.6 km\",\n" +
                "                  \"value\" : 602\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 min\",\n" +
                "                  \"value\" : 82\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 102799\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 49 mins\",\n" +
                "                  \"value\" : 6567\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"110 km\",\n" +
                "                  \"value\" : 110150\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 44 mins\",\n" +
                "                  \"value\" : 6220\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.2 km\",\n" +
                "                  \"value\" : 7167\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 679\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"35.7 km\",\n" +
                "                  \"value\" : 35658\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"36 mins\",\n" +
                "                  \"value\" : 2183\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"104 km\",\n" +
                "                  \"value\" : 104308\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 53 mins\",\n" +
                "                  \"value\" : 6769\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"102 km\",\n" +
                "                  \"value\" : 101903\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 50 mins\",\n" +
                "                  \"value\" : 6573\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"32.5 km\",\n" +
                "                  \"value\" : 32481\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"48 mins\",\n" +
                "                  \"value\" : 2875\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 103352\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 48 mins\",\n" +
                "                  \"value\" : 6503\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get10() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"6 Sierpnia 1, 90-422 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 103226\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 51 mins\",\n" +
                "                  \"value\" : 6678\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 102602\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 50 mins\",\n" +
                "                  \"value\" : 6608\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"0.3 km\",\n" +
                "                  \"value\" : 266\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 mins\",\n" +
                "                  \"value\" : 95\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.6 km\",\n" +
                "                  \"value\" : 7637\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"19 mins\",\n" +
                "                  \"value\" : 1145\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"109 km\",\n" +
                "                  \"value\" : 109046\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 0 mins\",\n" +
                "                  \"value\" : 7202\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"86.6 km\",\n" +
                "                  \"value\" : 86563\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 30 mins\",\n" +
                "                  \"value\" : 5421\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.4 km\",\n" +
                "                  \"value\" : 4373\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"14 mins\",\n" +
                "                  \"value\" : 830\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.3 km\",\n" +
                "                  \"value\" : 3349\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 679\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"136 km\",\n" +
                "                  \"value\" : 135572\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 30 mins\",\n" +
                "                  \"value\" : 9015\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.8 km\",\n" +
                "                  \"value\" : 3764\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 651\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get9() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Grodziska 3A, 05-840 Brwinów, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"120 km\",\n" +
                "                  \"value\" : 119547\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 0 mins\",\n" +
                "                  \"value\" : 7224\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.5 km\",\n" +
                "                  \"value\" : 7545\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"12 mins\",\n" +
                "                  \"value\" : 711\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.8 km\",\n" +
                "                  \"value\" : 7832\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"13 mins\",\n" +
                "                  \"value\" : 757\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"119 km\",\n" +
                "                  \"value\" : 118961\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 58 mins\",\n" +
                "                  \"value\" : 7080\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"115 km\",\n" +
                "                  \"value\" : 115218\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 50 mins\",\n" +
                "                  \"value\" : 6571\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.8 km\",\n" +
                "                  \"value\" : 4834\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"8 mins\",\n" +
                "                  \"value\" : 501\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"36.4 km\",\n" +
                "                  \"value\" : 36382\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"38 mins\",\n" +
                "                  \"value\" : 2266\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"120 km\",\n" +
                "                  \"value\" : 120470\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 1 min\",\n" +
                "                  \"value\" : 7283\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"118 km\",\n" +
                "                  \"value\" : 118064\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 58 mins\",\n" +
                "                  \"value\" : 7087\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"25.6 km\",\n" +
                "                  \"value\" : 25633\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"39 mins\",\n" +
                "                  \"value\" : 2323\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"120 km\",\n" +
                "                  \"value\" : 120170\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 58 mins\",\n" +
                "                  \"value\" : 7104\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get8() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"15 Sierpnia 16, 96-515 Sochaczew, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"39.5 km\",\n" +
                "                  \"value\" : 39481\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"40 mins\",\n" +
                "                  \"value\" : 2423\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"81.1 km\",\n" +
                "                  \"value\" : 81141\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 22 mins\",\n" +
                "                  \"value\" : 4907\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"34.7 km\",\n" +
                "                  \"value\" : 34713\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"35 mins\",\n" +
                "                  \"value\" : 2107\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"34.6 km\",\n" +
                "                  \"value\" : 34612\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"36 mins\",\n" +
                "                  \"value\" : 2154\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"80.6 km\",\n" +
                "                  \"value\" : 80554\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 19 mins\",\n" +
                "                  \"value\" : 4764\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"76.8 km\",\n" +
                "                  \"value\" : 76811\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 11 mins\",\n" +
                "                  \"value\" : 4254\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"41.9 km\",\n" +
                "                  \"value\" : 41880\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"46 mins\",\n" +
                "                  \"value\" : 2786\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.7 km\",\n" +
                "                  \"value\" : 7666\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 643\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"82.1 km\",\n" +
                "                  \"value\" : 82063\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 23 mins\",\n" +
                "                  \"value\" : 4967\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"79.7 km\",\n" +
                "                  \"value\" : 79658\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 20 mins\",\n" +
                "                  \"value\" : 4770\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"56.7 km\",\n" +
                "                  \"value\" : 56697\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 12 mins\",\n" +
                "                  \"value\" : 4310\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"81.8 km\",\n" +
                "                  \"value\" : 81764\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 20 mins\",\n" +
                "                  \"value\" : 4788\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get7() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"79.7 km\",\n" +
                "                  \"value\" : 79677\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 18 mins\",\n" +
                "                  \"value\" : 4654\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"118 km\",\n" +
                "                  \"value\" : 117902\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 55 mins\",\n" +
                "                  \"value\" : 6901\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.9 km\",\n" +
                "                  \"value\" : 2940\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"10 mins\",\n" +
                "                  \"value\" : 595\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 102771\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 48 mins\",\n" +
                "                  \"value\" : 6450\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"102 km\",\n" +
                "                  \"value\" : 102147\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 46 mins\",\n" +
                "                  \"value\" : 6380\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.8 km\",\n" +
                "                  \"value\" : 2807\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"9 mins\",\n" +
                "                  \"value\" : 526\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"5.7 km\",\n" +
                "                  \"value\" : 5703\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"13 mins\",\n" +
                "                  \"value\" : 791\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"109 km\",\n" +
                "                  \"value\" : 108591\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 56 mins\",\n" +
                "                  \"value\" : 6975\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"86.1 km\",\n" +
                "                  \"value\" : 86108\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 27 mins\",\n" +
                "                  \"value\" : 5194\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"5.3 km\",\n" +
                "                  \"value\" : 5267\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"14 mins\",\n" +
                "                  \"value\" : 863\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.2 km\",\n" +
                "                  \"value\" : 2224\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"7 mins\",\n" +
                "                  \"value\" : 435\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"135 km\",\n" +
                "                  \"value\" : 135117\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 26 mins\",\n" +
                "                  \"value\" : 8787\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.6 km\",\n" +
                "                  \"value\" : 4625\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 637\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get6() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Żelazowa Wola 15, 96-503, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"90.9 km\",\n" +
                "                  \"value\" : 90863\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 30 mins\",\n" +
                "                  \"value\" : 5393\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.8 km\",\n" +
                "                  \"value\" : 7772\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 653\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"36.4 km\",\n" +
                "                  \"value\" : 36424\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"37 mins\",\n" +
                "                  \"value\" : 2241\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"91.5 km\",\n" +
                "                  \"value\" : 91545\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 31 mins\",\n" +
                "                  \"value\" : 5486\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"35.8 km\",\n" +
                "                  \"value\" : 35773\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"36 mins\",\n" +
                "                  \"value\" : 2188\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"36.4 km\",\n" +
                "                  \"value\" : 36376\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"38 mins\",\n" +
                "                  \"value\" : 2270\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"91.0 km\",\n" +
                "                  \"value\" : 90959\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 29 mins\",\n" +
                "                  \"value\" : 5343\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"87.2 km\",\n" +
                "                  \"value\" : 87216\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 21 mins\",\n" +
                "                  \"value\" : 4833\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"41.2 km\",\n" +
                "                  \"value\" : 41218\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"46 mins\",\n" +
                "                  \"value\" : 2737\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"86 m\",\n" +
                "                  \"value\" : 86\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 min\",\n" +
                "                  \"value\" : 10\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"92.5 km\",\n" +
                "                  \"value\" : 92468\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 32 mins\",\n" +
                "                  \"value\" : 5545\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"90.1 km\",\n" +
                "                  \"value\" : 90062\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 29 mins\",\n" +
                "                  \"value\" : 5349\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"52.7 km\",\n" +
                "                  \"value\" : 52694\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 6 mins\",\n" +
                "                  \"value\" : 3934\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"92.2 km\",\n" +
                "                  \"value\" : 92168\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 29 mins\",\n" +
                "                  \"value\" : 5367\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get5() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Żelazowa Wola 15, 96-503, Poland\",\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Skarbowa 28, 91-473 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"84.9 km\",\n" +
                "                  \"value\" : 84898\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 23 mins\",\n" +
                "                  \"value\" : 4965\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.5 km\",\n" +
                "                  \"value\" : 3523\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 634\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"78.4 km\",\n" +
                "                  \"value\" : 78381\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 14 mins\",\n" +
                "                  \"value\" : 4417\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"117 km\",\n" +
                "                  \"value\" : 116606\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 51 mins\",\n" +
                "                  \"value\" : 6663\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"5.1 km\",\n" +
                "                  \"value\" : 5072\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"15 mins\",\n" +
                "                  \"value\" : 919\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 103028\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 46 mins\",\n" +
                "                  \"value\" : 6366\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"102 km\",\n" +
                "                  \"value\" : 102404\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 45 mins\",\n" +
                "                  \"value\" : 6296\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"5.3 km\",\n" +
                "                  \"value\" : 5337\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"17 mins\",\n" +
                "                  \"value\" : 1014\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.3 km\",\n" +
                "                  \"value\" : 3304\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"7 mins\",\n" +
                "                  \"value\" : 410\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"109 km\",\n" +
                "                  \"value\" : 108848\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 55 mins\",\n" +
                "                  \"value\" : 6890\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"84.8 km\",\n" +
                "                  \"value\" : 84812\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 23 mins\",\n" +
                "                  \"value\" : 4956\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.0 km\",\n" +
                "                  \"value\" : 6975\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"20 mins\",\n" +
                "                  \"value\" : 1190\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.0 km\",\n" +
                "                  \"value\" : 2955\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"8 mins\",\n" +
                "                  \"value\" : 505\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"134 km\",\n" +
                "                  \"value\" : 133822\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 23 mins\",\n" +
                "                  \"value\" : 8550\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"9.3 km\",\n" +
                "                  \"value\" : 9291\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"18 mins\",\n" +
                "                  \"value\" : 1075\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get4() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Skarbowa 28, 91-473 Łódź, Poland\",\n" +
                "      \"Żelazowa Wola 15, 96-503, Poland\",\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Przy Parku 95, 05-080 Lipków, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"125 km\",\n" +
                "                  \"value\" : 124988\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 0 mins\",\n" +
                "                  \"value\" : 7205\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"37.1 km\",\n" +
                "                  \"value\" : 37075\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"37 mins\",\n" +
                "                  \"value\" : 2202\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"127 km\",\n" +
                "                  \"value\" : 127202\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 7 mins\",\n" +
                "                  \"value\" : 7619\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"44.8 km\",\n" +
                "                  \"value\" : 44847\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"48 mins\",\n" +
                "                  \"value\" : 2855\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"21.2 km\",\n" +
                "                  \"value\" : 21157\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"28 mins\",\n" +
                "                  \"value\" : 1654\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"128 km\",\n" +
                "                  \"value\" : 127885\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 9 mins\",\n" +
                "                  \"value\" : 7712\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"29.1 km\",\n" +
                "                  \"value\" : 29119\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"33 mins\",\n" +
                "                  \"value\" : 1998\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"29.7 km\",\n" +
                "                  \"value\" : 29721\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"35 mins\",\n" +
                "                  \"value\" : 2080\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"127 km\",\n" +
                "                  \"value\" : 127298\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 6 mins\",\n" +
                "                  \"value\" : 7569\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"124 km\",\n" +
                "                  \"value\" : 123555\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 58 mins\",\n" +
                "                  \"value\" : 7059\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"26.0 km\",\n" +
                "                  \"value\" : 25951\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"36 mins\",\n" +
                "                  \"value\" : 2150\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"37.2 km\",\n" +
                "                  \"value\" : 37161\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"37 mins\",\n" +
                "                  \"value\" : 2212\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"129 km\",\n" +
                "                  \"value\" : 128807\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 10 mins\",\n" +
                "                  \"value\" : 7772\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"126 km\",\n" +
                "                  \"value\" : 126402\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 6 mins\",\n" +
                "                  \"value\" : 7575\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"20.1 km\",\n" +
                "                  \"value\" : 20056\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"39 mins\",\n" +
                "                  \"value\" : 2332\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"129 km\",\n" +
                "                  \"value\" : 128508\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 7 mins\",\n" +
                "                  \"value\" : 7593\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get3() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Przy Parku 95, 05-080 Lipków, Poland\",\n" +
                "      \"Skarbowa 28, 91-473 Łódź, Poland\",\n" +
                "      \"Żelazowa Wola 15, 96-503, Poland\",\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Piotrkowska 91/90, 90-423 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"128 km\",\n" +
                "                  \"value\" : 127775\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 7 mins\",\n" +
                "                  \"value\" : 7623\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.4 km\",\n" +
                "                  \"value\" : 4408\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"12 mins\",\n" +
                "                  \"value\" : 707\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"87.7 km\",\n" +
                "                  \"value\" : 87673\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 31 mins\",\n" +
                "                  \"value\" : 5433\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"1.4 km\",\n" +
                "                  \"value\" : 1417\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"4 mins\",\n" +
                "                  \"value\" : 263\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"81.2 km\",\n" +
                "                  \"value\" : 81156\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 21 mins\",\n" +
                "                  \"value\" : 4885\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"119 km\",\n" +
                "                  \"value\" : 119381\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 59 mins\",\n" +
                "                  \"value\" : 7131\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"0.7 km\",\n" +
                "                  \"value\" : 666\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 mins\",\n" +
                "                  \"value\" : 145\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"104 km\",\n" +
                "                  \"value\" : 104250\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 51 mins\",\n" +
                "                  \"value\" : 6681\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"104 km\",\n" +
                "                  \"value\" : 103625\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 50 mins\",\n" +
                "                  \"value\" : 6610\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"0.9 km\",\n" +
                "                  \"value\" : 932\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"4 mins\",\n" +
                "                  \"value\" : 240\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.2 km\",\n" +
                "                  \"value\" : 7204\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"16 mins\",\n" +
                "                  \"value\" : 951\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"110 km\",\n" +
                "                  \"value\" : 110070\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 0 mins\",\n" +
                "                  \"value\" : 7205\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"87.6 km\",\n" +
                "                  \"value\" : 87587\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 30 mins\",\n" +
                "                  \"value\" : 5424\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.9 km\",\n" +
                "                  \"value\" : 2854\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"8 mins\",\n" +
                "                  \"value\" : 507\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"1.8 km\",\n" +
                "                  \"value\" : 1830\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"6 mins\",\n" +
                "                  \"value\" : 356\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"137 km\",\n" +
                "                  \"value\" : 136596\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 30 mins\",\n" +
                "                  \"value\" : 9018\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.1 km\",\n" +
                "                  \"value\" : 2123\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"7 mins\",\n" +
                "                  \"value\" : 445\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get2() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Piotrkowska 91/90, 90-423 Łódź, Poland\",\n" +
                "      \"Przy Parku 95, 05-080 Lipków, Poland\",\n" +
                "      \"Skarbowa 28, 91-473 Łódź, Poland\",\n" +
                "      \"Żelazowa Wola 15, 96-503, Poland\",\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Chyleniecka 4, 99-416 Nieborów, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"64.1 km\",\n" +
                "                  \"value\" : 64130\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4080\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.5 km\",\n" +
                "                  \"value\" : 63488\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 3 mins\",\n" +
                "                  \"value\" : 3796\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"61.9 km\",\n" +
                "                  \"value\" : 61900\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 2 mins\",\n" +
                "                  \"value\" : 3717\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"31.4 km\",\n" +
                "                  \"value\" : 31358\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"31 mins\",\n" +
                "                  \"value\" : 1879\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"64.1 km\",\n" +
                "                  \"value\" : 64114\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 9 mins\",\n" +
                "                  \"value\" : 4132\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"24.2 km\",\n" +
                "                  \"value\" : 24245\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"22 mins\",\n" +
                "                  \"value\" : 1335\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"51.4 km\",\n" +
                "                  \"value\" : 51438\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"51 mins\",\n" +
                "                  \"value\" : 3048\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"64.8 km\",\n" +
                "                  \"value\" : 64796\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 10 mins\",\n" +
                "                  \"value\" : 4225\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"46.8 km\",\n" +
                "                  \"value\" : 46755\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"44 mins\",\n" +
                "                  \"value\" : 2659\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"46.7 km\",\n" +
                "                  \"value\" : 46654\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"45 mins\",\n" +
                "                  \"value\" : 2706\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"64.2 km\",\n" +
                "                  \"value\" : 64210\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4082\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"60.5 km\",\n" +
                "                  \"value\" : 60466\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 0 mins\",\n" +
                "                  \"value\" : 3572\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"53.9 km\",\n" +
                "                  \"value\" : 53922\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"56 mins\",\n" +
                "                  \"value\" : 3339\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"31.3 km\",\n" +
                "                  \"value\" : 31272\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"31 mins\",\n" +
                "                  \"value\" : 1870\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"65.7 km\",\n" +
                "                  \"value\" : 65719\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 11 mins\",\n" +
                "                  \"value\" : 4284\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.3 km\",\n" +
                "                  \"value\" : 63313\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4088\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"72.3 km\",\n" +
                "                  \"value\" : 72309\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 27 mins\",\n" +
                "                  \"value\" : 5191\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"65.4 km\",\n" +
                "                  \"value\" : 65419\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4105\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get1() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Chyleniecka 4, 99-416 Nieborów, Poland\",\n" +
                "      \"Piotrkowska 91/90, 90-423 Łódź, Poland\",\n" +
                "      \"Przy Parku 95, 05-080 Lipków, Poland\",\n" +
                "      \"Skarbowa 28, 91-473 Łódź, Poland\",\n" +
                "      \"Żelazowa Wola 15, 96-503, Poland\",\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Piotrkowska 74, 90-111 Łódź, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.8 km\",\n" +
                "                  \"value\" : 63822\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 10 mins\",\n" +
                "                  \"value\" : 4206\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"1.6 km\",\n" +
                "                  \"value\" : 1571\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"5 mins\",\n" +
                "                  \"value\" : 303\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"127 km\",\n" +
                "                  \"value\" : 126803\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 7 mins\",\n" +
                "                  \"value\" : 7597\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"6.0 km\",\n" +
                "                  \"value\" : 5979\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"17 mins\",\n" +
                "                  \"value\" : 1009\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"86.7 km\",\n" +
                "                  \"value\" : 86701\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 30 mins\",\n" +
                "                  \"value\" : 5408\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.0 km\",\n" +
                "                  \"value\" : 2988\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"9 mins\",\n" +
                "                  \"value\" : 566\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"80.2 km\",\n" +
                "                  \"value\" : 80184\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 21 mins\",\n" +
                "                  \"value\" : 4859\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"118 km\",\n" +
                "                  \"value\" : 118409\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 58 mins\",\n" +
                "                  \"value\" : 7105\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"2.2 km\",\n" +
                "                  \"value\" : 2238\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"7 mins\",\n" +
                "                  \"value\" : 448\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 103278\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 51 mins\",\n" +
                "                  \"value\" : 6655\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"103 km\",\n" +
                "                  \"value\" : 102654\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 50 mins\",\n" +
                "                  \"value\" : 6585\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"0.2 km\",\n" +
                "                  \"value\" : 158\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 min\",\n" +
                "                  \"value\" : 44\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"7.7 km\",\n" +
                "                  \"value\" : 7688\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"19 mins\",\n" +
                "                  \"value\" : 1122\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"109 km\",\n" +
                "                  \"value\" : 109098\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 0 mins\",\n" +
                "                  \"value\" : 7179\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"86.6 km\",\n" +
                "                  \"value\" : 86615\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 30 mins\",\n" +
                "                  \"value\" : 5399\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"4.4 km\",\n" +
                "                  \"value\" : 4425\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"14 mins\",\n" +
                "                  \"value\" : 810\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.4 km\",\n" +
                "                  \"value\" : 3401\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"11 mins\",\n" +
                "                  \"value\" : 659\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"136 km\",\n" +
                "                  \"value\" : 135624\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 hours 30 mins\",\n" +
                "                  \"value\" : 8992\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"3.8 km\",\n" +
                "                  \"value\" : 3816\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"10 mins\",\n" +
                "                  \"value\" : 628\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }

    private String get0() {
        return "{\n" +
                "   \"destination_addresses\" : [\n" +
                "      \"Piotrkowska 74, 90-111 Łódź, Poland\",\n" +
                "      \"Chyleniecka 4, 99-416 Nieborów, Poland\",\n" +
                "      \"Piotrkowska 91/90, 90-423 Łódź, Poland\",\n" +
                "      \"Przy Parku 95, 05-080 Lipków, Poland\",\n" +
                "      \"Skarbowa 28, 91-473 Łódź, Poland\",\n" +
                "      \"Żelazowa Wola 15, 96-503, Poland\",\n" +
                "      \"Rewolucji 1905 4, 90-273 Łódź-Śródmieście, Poland\",\n" +
                "      \"15 Sierpnia 16, 96-515 Sochaczew, Poland\",\n" +
                "      \"Grodziska 3A, 05-840 Brwinów, Poland\",\n" +
                "      \"6 Sierpnia 1, 90-422 Łódź, Poland\",\n" +
                "      \"Park im. hr. Skarbków, 3 Maja, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"W. Bartniaka 26, 05-825 Grodzisk Mazowiecki, Poland\",\n" +
                "      \"Stanisława Moniuszki 6, 90-111 Łódź, Poland\",\n" +
                "      \"Skrzydlata 64, 91-503 Łódź, Poland\",\n" +
                "      \"Nadarzyńska 4, 05-807 Żółwin, Poland\",\n" +
                "      \"Żelazowa Wola 14, 96-503, Poland\",\n" +
                "      \"Park, 90-001 Łódź, Poland\",\n" +
                "      \"Ogrodowa 19, 91-071 Łódź, Poland\",\n" +
                "      \"Żurawia 22, 00-515 Warszawa, Poland\",\n" +
                "      \"aleja Adama Mickiewicza 1366, 90-050 Łódź, Poland\"\n" +
                "   ],\n" +
                "   \"origin_addresses\" : [ \"Nieborów 175, 99-416, Poland\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.3 km\",\n" +
                "                  \"value\" : 63316\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4081\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"1.6 km\",\n" +
                "                  \"value\" : 1557\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"2 mins\",\n" +
                "                  \"value\" : 106\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.1 km\",\n" +
                "                  \"value\" : 63078\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 7 mins\",\n" +
                "                  \"value\" : 4031\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"65.0 km\",\n" +
                "                  \"value\" : 65045\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 5 mins\",\n" +
                "                  \"value\" : 3903\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"60.8 km\",\n" +
                "                  \"value\" : 60848\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 1 min\",\n" +
                "                  \"value\" : 3668\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"31.7 km\",\n" +
                "                  \"value\" : 31721\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"32 mins\",\n" +
                "                  \"value\" : 1928\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.1 km\",\n" +
                "                  \"value\" : 63062\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4083\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"25.2 km\",\n" +
                "                  \"value\" : 25204\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"23 mins\",\n" +
                "                  \"value\" : 1380\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"53.0 km\",\n" +
                "                  \"value\" : 52995\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"53 mins\",\n" +
                "                  \"value\" : 3155\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.7 km\",\n" +
                "                  \"value\" : 63744\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 10 mins\",\n" +
                "                  \"value\" : 4176\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"48.3 km\",\n" +
                "                  \"value\" : 48313\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"46 mins\",\n" +
                "                  \"value\" : 2766\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"48.2 km\",\n" +
                "                  \"value\" : 48211\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"47 mins\",\n" +
                "                  \"value\" : 2813\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"63.2 km\",\n" +
                "                  \"value\" : 63158\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 7 mins\",\n" +
                "                  \"value\" : 4033\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"59.4 km\",\n" +
                "                  \"value\" : 59414\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"59 mins\",\n" +
                "                  \"value\" : 3523\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"55.5 km\",\n" +
                "                  \"value\" : 55479\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"57 mins\",\n" +
                "                  \"value\" : 3445\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"31.6 km\",\n" +
                "                  \"value\" : 31635\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"32 mins\",\n" +
                "                  \"value\" : 1919\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"64.7 km\",\n" +
                "                  \"value\" : 64667\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 11 mins\",\n" +
                "                  \"value\" : 4235\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"62.3 km\",\n" +
                "                  \"value\" : 62261\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 7 mins\",\n" +
                "                  \"value\" : 4039\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"73.9 km\",\n" +
                "                  \"value\" : 73866\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 28 mins\",\n" +
                "                  \"value\" : 5298\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"64.4 km\",\n" +
                "                  \"value\" : 64367\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"1 hour 8 mins\",\n" +
                "                  \"value\" : 4056\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }
}
