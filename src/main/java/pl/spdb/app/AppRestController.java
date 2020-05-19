package pl.spdb.app;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.spdb.app.algorithm.WaypointFinder;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.model.route.Routes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class AppRestController {

    @Autowired
    DistanceMatrixService distanceMatrixService;

    @Autowired
    PlacesService placesService;

    @Autowired
    WaypointFinder waypointFinder;

    @GetMapping("/dev/mtx")
    public @ResponseBody String matrix() {
        List<String> origins = new ArrayList<>(Arrays.asList("Boston,MA", "Charlestown,MA"));
        List<String> destinations = new ArrayList<>(Arrays.asList("Lexington,MA", "Concord,MA"));
        return distanceMatrixService.getDistanceMatrix(origins, destinations);
    }

    @GetMapping("/dev/test")
    public @ResponseBody String testing() {
        String json = getRoute();
        Gson gson = new Gson();
        Routes routes = gson.fromJson(json, Routes.class);
        return gson.toJson(waypointFinder.findWaypoints(routes));
    }

    @GetMapping("/api/categories")
    public ResponseEntity<Map<String, String>> categories() {
        return ResponseEntity.ok(placesService.getCategories());
    }

    private String getRoute() {
        return "{\n" +
                "   \"geocoded_waypoints\" : [\n" +
                "      {\n" +
                "         \"geocoder_status\" : \"OK\",\n" +
                "         \"place_id\" : \"ChIJyfm1gl_MHkcRCC9LvPySxRg\",\n" +
                "         \"types\" : [ \"establishment\", \"point_of_interest\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"geocoder_status\" : \"OK\",\n" +
                "         \"place_id\" : \"ChIJaYRG9mlbFkcRU7JNJ-Vy2p8\",\n" +
                "         \"types\" : [ \"premise\" ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"routes\" : [\n" +
                "      {\n" +
                "         \"bounds\" : {\n" +
                "            \"northeast\" : {\n" +
                "               \"lat\" : 52.2369723,\n" +
                "               \"lng\" : 21.0848496\n" +
                "            },\n" +
                "            \"southwest\" : {\n" +
                "               \"lat\" : 50.0476866,\n" +
                "               \"lng\" : 19.9443626\n" +
                "            }\n" +
                "         },\n" +
                "         \"copyrights\" : \"Map data ©2020 Google\",\n" +
                "         \"legs\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"297 km\",\n" +
                "                  \"value\" : 296764\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"3 hours 34 mins\",\n" +
                "                  \"value\" : 12850\n" +
                "               },\n" +
                "               \"end_address\" : \"Bożego Ciała 29, 33-332 Kraków, Poland\",\n" +
                "               \"end_location\" : {\n" +
                "                  \"lat\" : 50.0496509,\n" +
                "                  \"lng\" : 19.9443626\n" +
                "               },\n" +
                "               \"start_address\" : \"Świętokrzyska 12, 00-044 Warszawa, Poland\",\n" +
                "               \"start_location\" : {\n" +
                "                  \"lat\" : 52.2369723,\n" +
                "                  \"lng\" : 21.0175713\n" +
                "               },\n" +
                "               \"steps\" : [\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.6 km\",\n" +
                "                        \"value\" : 633\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"2 mins\",\n" +
                "                        \"value\" : 131\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.2352579,\n" +
                "                        \"lng\" : 21.0087054\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Head \\u003cb\\u003ewest\\u003c/b\\u003e on \\u003cb\\u003eŚwiętokrzyska\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eDW719\\u003c/b\\u003e toward \\u003cb\\u003eKubusia Puchatka\\u003c/b\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"apy}Hy~g_Cb@`EFb@Db@VtBLbAbAtIJ`ARdBr@xGR`Bl@fFFh@Dd@TtB\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.2369723,\n" +
                "                        \"lng\" : 21.0175713\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.6 km\",\n" +
                "                        \"value\" : 640\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"2 mins\",\n" +
                "                        \"value\" : 115\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.2299527,\n" +
                "                        \"lng\" : 21.0112515\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e onto \\u003cb\\u003eMarszałkowska\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"key}Hmgf_CF`@@NBN\\\\URKt@]hAk@\\\\OBCTMxDsBp@[xAs@n@_@LGl@]h@W|@e@f@UXMNGZI~@YNGJEFC^C`@A\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.2352579,\n" +
                "                        \"lng\" : 21.0087054\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.4 km\",\n" +
                "                        \"value\" : 449\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 65\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.2285204,\n" +
                "                        \"lng\" : 21.0051058\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eal. Jerozolimskie\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eDW631\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"edx}Hiwf_CJb@JVRp@Nn@F\\\\F\\\\N`Al@~Ef@~DRfBP|AJ~@L`AVhBJv@Fd@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.2299527,\n" +
                "                        \"lng\" : 21.0112515\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.2 km\",\n" +
                "                        \"value\" : 213\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 43\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.2279632,\n" +
                "                        \"lng\" : 21.0021163\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue straight to stay on \\u003cb\\u003eal. Jerozolimskie\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eDW631\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"straight\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"g{w}H}pe_CXbCb@lDDV@RBP@L?B?PBPHp@T|B\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.2285204,\n" +
                "                        \"lng\" : 21.0051058\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.9 km\",\n" +
                "                        \"value\" : 935\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"3 mins\",\n" +
                "                        \"value\" : 152\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.225146,\n" +
                "                        \"lng\" : 20.9891921\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"At the roundabout, take the \\u003cb\\u003e1st\\u003c/b\\u003e exit and stay on \\u003cb\\u003eal. Jerozolimskie\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eDW631\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"roundabout-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"www}Hg~d_C@PBN@B@L@B@H@DBNBNBL?@P|@\\\\dBBLHb@Hr@t@vFBVd@nDRzAFj@Hr@p@nFBT`AxHhApID^|@~G^nC?@`@vD\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.2279632,\n" +
                "                        \"lng\" : 21.0021163\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"6.2 km\",\n" +
                "                        \"value\" : 6186\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"7 mins\",\n" +
                "                        \"value\" : 407\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.1905481,\n" +
                "                        \"lng\" : 20.919931\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"At \\u003cb\\u003eplac Artura Zawiszy\\u003c/b\\u003e, take the \\u003cb\\u003e1st\\u003c/b\\u003e exit onto \\u003cb\\u003eal. Jerozolimskie\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eDW717\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eContinue to follow al. Jerozolimskie\\u003c/div\\u003e\",\n" +
                "                     \"maneuver\" : \"roundabout-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"efw}Hmmb_C\\\\fB?@@@Z~A\\\\~AZ|AZ~AVnAR|@bA~E`@nBVlAH`@VvAJd@TjAPr@`@tBFZDRz@vEj@xDd@hC\\\\jBH^F`@FXF`@Hb@~@zFr@pDb@bCd@fCp@pDV|AFXNj@BL@DLj@X|ATrALb@p@lBhB`H\\\\lAPn@`@vAv@jCd@`B^xAr@bD`ArEDDBBDJ`AnEBJl@lCh@|BR~@FRH\\\\?DXhA@D@F?BNl@?BT|@ZxAbAnE`@fBH^h@rBl@nBj@zAX~@rA`CpArB?@hH`MBDT^Zf@NXl@bAp@dA|ClF|AjCVb@\\\\j@?@R\\\\DHDHdBvC~CnF@BZf@`AbBlAxBp@jAxCfFvEhIPXNXd@v@JPLTLRNVNVzBtD|ApCh@~@vAvB`A|AHLn@dArC|ElArBVb@R^@?fB|CZf@|ClFT^BFfCvE~AvCLTrC~ENR`CjDdAnAjAzADFLPdBdDBDT`@r@jAlB`DTf@z@|AdDdGhDrGt@pApEjItBxD\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.225146,\n" +
                "                        \"lng\" : 20.9891921\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"2.8 km\",\n" +
                "                        \"value\" : 2782\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"2 mins\",\n" +
                "                        \"value\" : 102\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.1667087,\n" +
                "                        \"lng\" : 20.9084992\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eDK7\\u003c/b\\u003e (signs for \\u003cb\\u003eUrsynów\\u003c/b\\u003e)\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"}mp}Hq|t~B|AlCd@l@bBxBh@b@dAn@n@X`A\\\\j@PPDPBbARdBd@lCl@f@Ld@J~@T`AXrBf@~A`@RFxFvAF@~EpANDhBb@bBd@LDtBh@bB\\\\fDx@~A`@vDbAnBf@vD~@pBr@fBl@nAf@`Bp@~Al@hAd@`CfA`@RhD~Ax@^HDnGtCvDdB`FxBhAh@hAf@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.1905481,\n" +
                "                        \"lng\" : 20.919931\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"4.0 km\",\n" +
                "                        \"value\" : 3978\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"2 mins\",\n" +
                "                        \"value\" : 140\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.1357546,\n" +
                "                        \"lng\" : 20.8803644\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eDK7\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eS8\\u003c/b\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"}xk}Hcur~BdBv@t@ZhHbDxC|AnAr@x@d@vD`CbChBfIvGzApAzFxExDbDlEpDxCfCxCrCr@t@|A`B|CpDvAdBrAfBr@`AtAnBpCjErB~CrB`DnCfEdDhFlBvCxAzBbAvAzArBvAjBn@v@l@r@t@|@fArA~C`DxCnC~BtBfIbGt@h@vAx@bCxAnEnBdB|@VH`@L~Af@vFnB^L\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.1667087,\n" +
                "                        \"lng\" : 20.9084992\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"1.2 km\",\n" +
                "                        \"value\" : 1242\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 46\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 52.1250313,\n" +
                "                        \"lng\" : 20.8775854\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Take the exit toward \\u003cb\\u003eDK7\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"ramp-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"mwe}Hgem~B|HzDrAr@PL`Ah@rBjAzBjA`Aj@jAj@l@T`@Pb@PB@lA`@|Ab@v@RxAZ`@DB?fAHb@DL@tAAtAA`AIp@I~@O~Bo@z@WNGxB_A\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.1357546,\n" +
                "                        \"lng\" : 20.8803644\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"23.4 km\",\n" +
                "                        \"value\" : 23433\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"19 mins\",\n" +
                "                        \"value\" : 1159\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.9233035,\n" +
                "                        \"lng\" : 20.8513628\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eDK7\\u003c/b\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"mtc}H}sl~Bx@]DCNKTS|@_ANMxCoCXUh@m@bAgAf@k@dDiEfAwAPS`B{Bl@u@dB}BjCgDrBmC`BwBBEhA{AfBkB\\\\g@tA_Bj@w@t@o@f@c@z@s@`Aq@BA`Ao@BAVOx@c@z@_@ZM\\\\Mr@WJAbAWx@Or@Kf@G|@G\\\\Av@?R?p@@`@@l@DRBRBt@Hb@F|@PdATPDf@JzHzBn@PlBl@hAZxGlBdElAvEtAf@Nx@Tr@R`AZpA^^JVFRJ`AX|IjChI~BlDbAhI`Cd@LjDbAvEtApA^b@LzDfA`MnDdD`AfA^~Af@H@pBj@dD~@rDdAhBh@jFxAr@PpCz@r@TrA^`AZ~P~EtJpCbEjA~Ab@`Cp@~Ab@dFzAx@T~Br@PDlDdAv@RtEtAhJjC|Bn@hD`AZJfAZtJpChAZ\\\\HpMxD~DjAjGfB|DhAtCx@fBf@rBl@x@TtDfApJnCvDfA`@JlCv@nKzCv@TnDdAvF~A~Br@vMzDrA^lLdDjAZzGnBhBh@v@TVHpLhDtNbE|DfAnGhBpF~A`D|@z@V\\\\J^JpEpAt@V|@XtDxApJvDxJ~D`@PxGdCzGhCr@Zr@XbEbBtAj@DBr@VvBz@nFxBhGbCtChAn@X|ClAjHtC|B|@`A^pBv@`HnCHD|ClA~EnBbBp@hAb@zClAl@TvClApHtC~EnBjCdAlLrETLbIvCx@NpAVjB@d@CLA^CnAOl@OBAjCgAlKcEvB{@~QmHt@WjBm@\\\\EtBU^?PAl@?ZB\\\\DN@F@`@FRDx@Zl@Vj@\\\\B@z@l@z@n@fA|@fA~@FFrD~CrD`D~@x@|@t@jC|BZV`Ax@b@^TRjEzDl@l@fA|@hCrBdDtC@@ZX@@zBnB^ZxBnBj@d@x@j@r@f@PH|@\\\\NDh@N`ALhAJ|@@v@?t@In@KnAYb@Kb@Kb@Mf@ODATEzD{@xJwCtDeArCs@rBi@zI{BxF{ArEkA`IsB~Ac@jA]v`@oK|Cy@`@K|NyDfBe@b@KjCs@^IP?J@n@HbADn@BvAEf@KlA]\\\\Kb@MlDeAfDuAf@S`CgA`CgArAe@`EeA`@KzAc@rA_@bG{Av@Q|Bk@bAWPAVAj@CjAEdAIdAIFAl@Gz@Mb@Gz@SxEqAB?XITG`@Oj@Y^U`@Qh@OxA]d@Mp@OVGlA[`Ci@pD}@lEgA~HsBhGwAdEcArBk@jBa@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 52.1250313,\n" +
                "                        \"lng\" : 20.8775854\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"2.7 km\",\n" +
                "                        \"value\" : 2710\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"3 mins\",\n" +
                "                        \"value\" : 182\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 51.90017049999999,\n" +
                "                        \"lng\" : 20.8598399\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue straight to stay on \\u003cb\\u003eDK7\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"straight\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"sg|{H_pg~B|HsBzC{@z@S\\\\E\\\\Ch@AP@LAPCd@Ib@MrCy@t@Up@SXEVAR?LBNBd@FN@P?b@GTE\\\\K`@MXQNKPOv@w@^YROXSLG^K|HiBrAYb@GnAOlAUbB_@dDy@d@MXORMJIJMHKVg@JMHIbDw@@ABADCNKPOPYTc@`@_ATg@LSNS^e@VSVONIFCHAnBi@zAc@r@UT@P?H@LBNLHLFNFPBFBLDLDLDJFHNHHDLBF@H?H?d@GRGRG~NwD@AvGcBnBg@zC{@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.9233035,\n" +
                "                        \"lng\" : 20.8513628\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"190 km\",\n" +
                "                        \"value\" : 190406\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 hour 42 mins\",\n" +
                "                        \"value\" : 6097\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.4950178,\n" +
                "                        \"lng\" : 20.1838456\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eE77\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eS7\\u003c/b\\u003e (signs for \\u003cb\\u003eKraków\\u003c/b\\u003e)\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eContinue to follow S7\\u003c/div\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"aww{H_ei~BdDy@|Cs@tAQnAK|@?l@AjADlAPp@Hh@L~@Xz@\\\\z@b@h@Xf@\\\\bChBbCjB\\\\XlGxExAbA|@f@bAf@f@Rr@Tp@P~@TbALr@HR@T@`ADjA@jBBrC@fCBxAB~KP~BBtL@zDFbEFl@@p@Dv@HdARx@Pt@TtAf@?@NDPH`@NpCfAhEbBlEfBxBx@dDpAfDpAfIdDfBn@jBn@x@Tj@Nh@JlAVh@Ff@HL@v@Dz@BrA?t@Al@GrBWh@Kn@O`@Kp@Uz@[bAe@|@i@JETQl@a@t@m@jAaAp@q@v@}@f@o@p@aA|@wAx@wAp@uAt@aBt@cBr@gB~@cCt@mB|BgGx@_CxEyMl@}AtAmD~@qBhAuBp@aA|@oAtA}Ar@s@`@]b@]f@c@r@g@j@c@lAw@rA_AjDaCf@]|EgDlDaCp@e@h@c@h@c@fGgErCoBjDaC`Ao@t@i@bC_BpBoA|Aw@xAk@nBi@nA[FC|@Q|Cw@PE`@K|HmBlEu@|Dw@|FwAzO_EjNmD~I{BtEkAhAYhBc@hCm@tJ{BlHiBbLsCpPaE|Bk@jIsB`Dw@rA]vD_AvIuBfQiEn@Q|JcCXIfJ{Bb@MnG}AHAXI`KeCxOyDdZmHj@O~L{CnA[`NeDHC~L{CFAj@OvIuBzKoCFAb@M|HoBjU}FvHmBlGiBz@WHCXI|@Y~EaB|@YfBk@tGuBFCjFaBFCXKHCz@W|WwI~@[tQuFrAa@dCw@pAg@bGmBrIqCtImC~@[`@MbMaEjRiGbGoBXItImCzBu@bFaBLEbFaBtC_AfBk@pRiG~HgCtDkA~E_BfDeApAc@bEsAvNwEbT}GvGuBlDiA`@MdA]|VcI`@M~WsIdA[`@OlDiA~JaDbM}D|VcI`@OlDgAjC{@fBk@xQ}FjGoBhBk@tLcEx@UnAa@rJ}CNGrKeDxJwCbIaC|Ak@xI_DhGyBnAg@FCt@e@p@_@l@Yl@[vBiA|@g@VOxA{@`@WZQDE^URMvByAv@k@@AfDiCv@o@|@u@dA_AnCyB|@{@zCaDvFmGnAuAjCaDz@gAp@{@zAkBv@{@zAiBtA_B~BiCl@s@l@o@`AiAvAaBjAwA\\\\_@Za@b@e@xAgBx@aAhD_EVYhD_EvAaB~AkB|AgBvAeBPQJOpA{AlBwBlAwAtCiD~DsEbDyDjC}Cz@aAZ_@nAwAlAyAfDyD\\\\a@NQJMfAoAvCcDzB_CrBsBzBuBpBeBXWdB_B|EkE|@y@\\\\]xEiEXWd@a@tGeGjE_EfGgFl@g@~A{@zAw@hBa@~A[l@MzBQpBH`BPD@~Cp@lDpA`Bt@tCnAbAd@dAd@|OfH`@Pf@TrH|C`Bp@nAh@`Cx@bAXv@TxCf@nCXbA?|AFPAbBGd@AbBElAQXEzBa@ZGt@OvAc@bAa@`@QLEpBaAf@Y`@S`@SJGzFoD`@UfHmE|CmBxH{E`CqA~BuAdGoDlEqCvA}@r@e@r@c@jBiA`JwF`Am@^U@AzFkDfHkEd@[~CoB\\\\SxEwCfFaDrG{DrBsAb@W`@W`Am@xPmK`IaFvD}BjNqIhIgFbDqBhHmE~AaArNwI~@k@@?nOoJ^WJGvA{@fKuGd@SpAw@vFkDj@]xGcEb@_@\\\\[BC`A_Az@aAr@y@n@w@l@y@DEn@aAh@aAhCcGlEwIfEoKnBqE~HwQzEwK|CeHbEkJv@eBrA_DzB_FbAwBp@oAvAaCtAqBv@{@VY`CyBr@k@d@a@nA_AzAw@r@[`A]ZMn@Sv@Sh@Kx@Mt@MxAMbDQ`BMxAMr@Gl@Iz@QbA[^K|@a@nAm@j@a@r@e@`@_@n@k@^a@|@cABE\\\\a@@AV_@`@k@`@s@t@oAXo@d@eAf@mAl@{AbAgCVk@Na@DKjB{EdBeE~BeGdBiEpDeJdCqGzBwFb@iAFOhBsErAiDhBoEvBqFtBkF~AeEbCiG~B}FvBoF@EVm@tBmFd@qAbB}DhBwEj@{AbEcLxAwDlJeV`HiQpFkNnOg`@pAmC`E{HZm@rEgIR[R[zAmB~G_IFEzEsEvAgAROpH}E~DqB|Am@hAc@jEoAb@KdA[TGvFcAn@GtCYpFOfD@xBDf@BT@xCTfFr@H@b@JrKtBRDnGlAxCl@TDTFjATvHzAhB^`@HvFhAhDv@ZJZH|GrB|@XfBl@dCx@d@RtHzCzD|AtGrC`@Pb@Nn@V`MnFlAl@dDbBhGxDhF`E^Z\\\\ZnFvE^\\\\`@\\\\zDjDrOdNf@d@h@f@jYdWnBdBhErD^Zr@l@vErDrFbEn@d@n@b@dCfBbEfDnChCrCnCnCpCdBtB\\\\`@\\\\^JN|FjHTXTV~FjHdBjB\\\\^hAnAl@l@hCdClAfA|ArA\\\\ZdE`D|CxB`An@~DjCdBfAvBtAdKvGp@`@fDvBZTpD`CrGfE|FrDtH|E|OdKbElChEnC|@j@h@\\\\LHbEjCfFjCvF`CfCz@rEjAxE`A`G~@nKv@|GX~Lt@T@rJh@tAFfO~@n@Bn@Dh@DlDPdJf@b@B~G^nE`@lALnIx@`H~@fJxAjATlCf@`BZdAPbARB?dCd@HBlFbAxKtBjDl@dAPfARZFXDjBZzCf@lEn@jBXtAVb@F@@f@FLB`ANb@FhEj@bQ`C@?`@Db@FbFn@hFfAj@LjEpAdCbAdBr@XJj@VfCjAFB|At@bAh@xC|A|DjBzDvAbBh@dBf@RDzBj@`@JPDr@NbB\\\\lAThHv@N@dBFfABfADdABN@R?`ABhBFn@@dDRvCNnE^`F`@~ARvBXl@HTDTBxCd@zBZfIbBlEbArCt@pCv@H@`Ct@PDPDj@LlA\\\\|IfCnA^x@Vb@LlDbAhBh@dAZVHXHtA`@jCt@dCr@lBj@`AXrD|@nAZrDt@`CZtC`@ZBvEd@t@Dv@Fj@D~@DhDPdGFB?^?bDElHY~AGzAMNAlAIdAIlL{@b@Eb@C`BMl@EfAGx@Ed@Cd@EfAG|@EpAIpDWx@GxBO|E[bBMb@CnBMjIg@|DIdDBnEVjEn@tCf@hB\\\\bBZD@zAXP@PBjANfALXDzDNfECNChDYjCa@n@OxBi@`FcBxBaAJE^ObAa@lAe@HCbA]v@Yp@OVEzBg@rB[v@I~@EvIIp@@pCAtB?~B@dD@rEDzADtA?\\\\@Z?xE`@fDp@pCr@RDNFvDzAvEdChChB^V\\\\Xf@\\\\nCnClAnALNl@p@|@fApDrElFlHlFnHjJjNpAnBXd@|CxEdBlC|FzItAtBxD~FpKpPb@p@Zd@pGtJxQ~X|AhCzF~IxBdDLTpIjMlIpMjDpFp@dAlD|Fh@v@Zf@lFbKbClF`@~@b@~@~F|NpEtLTl@JVHTTl@FRjPzb@tBpFjBbFj@zA`AfC~HvSt@nBtAtDTl@pKbYdBtEj@xATl@j@zA`AfClBbFzDdKjAzCTl@`AfC~KtYpOja@hBxEXv@vAtDTj@Tl@|GnQvDxJnJtVfCvGbKfXVp@`G|ONVBHlDfJlDbJpBhF~@`CTl@Pb@P`@nBpFbBlF`BnFlBdGfKr]hLf`@DNfCpIRn@Pn@`BpFbAhD~BdIrBbHlAxDb@zAp@bCdA`DFRXz@f@tAp@`BdCvFhAzBx@tAXd@tAtBr@bAzBtCdClCtBhBl@d@vB~AZRjBhA~@b@fAh@`Bv@nCt@vA`@LBhBb@xANr@FrBRtBFxBCD?pCDdB?bAArEDb@?jB?vC?jBB|@B~BLp@FnCZbDh@vD|@lA^jErAjKvDjCbArL|DdA^b@N`@NbA\\\\lBp@`OpFdGnBn@Tz@XjDpAd@NdEvAzFvB`C`A|CrA~KpFlH~DLHvBfAbAb@dBr@rAd@jA^bCn@pAZhEr@vBTrAJbBHb@@zQ\\\\rBFfA@hBBb@@fA@~GJR@|DF|EH|ABtABb@@b@@bIL`BBbABnU^zDFbBBhCFrDFbHJjCDv@@~NTrFJzABpGXjBJnCN`Gt@vA^tAZx@PjA^t@Vn@TlBr@hAXrBdAzDfB@@ZT@@|BxAvBrA`Av@vAbA`BnAbCfCtArAhAlA?@VXz@dAlAvA~@fAFJLLlEjG@B@@X`@Xb@@@@@h@|@Zd@Xd@HNNT`@r@t@jA\\\\j@NVdBlCLPpB|CbBrCZb@Xf@pB~CrAxBpAnBTZvAdCnCdEjAlB`BfCb@j@HPz@~AlB`D|AdC\\\\l@nAbB~BzCjBjB`CbCp@j@BBbDdCZRxA~@j@Vv@^@?dBr@fAb@tBl@t@RlA\\\\fEl@tAJZ@pEFD?`FSVCvAM|Bc@j@M`Be@xCaAPI`@Q`Aa@@AvBeALIpByAn@c@h@_@l@i@j@c@x@s@z@{@\\\\c@\\\\]HKRUZ]x@_AhBaCbAqAbAoAvAkBNSjAaBjAwAhA}ANSjCcDdA_B@ClCmDRW~AoBhB}Bf@i@VWnDmD`CqB|ByAhG{C@AtHsB^Gv@MXEd@Eb@CVCVAzAMp@C~@CbAA|AD|@HV@rAN`@FfAHp@J\\\\F@?^FTF`@LbCv@rDtAJFlAj@FBDBbAh@B@\\\\Pl@XfCvAbAn@v@h@`@ZRNNLb@`@XVbAtAJRh@|@tAnCNTDJBDBHDDn@tA|@zAh@t@`@h@b@d@t@t@v@l@fCnBjA|@r@d@p@j@x@l@pB|A@@nCrBnA|@`DfC^Vb@ZlA~@NJj@`@v@h@PLp@^^RXJZJZHf@Jl@Fb@@^@^Bf@Af@CZCXCt@Ib@GLALCXEt@Kn@I\\\\ENCVCfAKhAKz@Iz@EtAIr@CD?p@Af@AP?v@CjBEjCCj@C`JKfCGPArLQ`@C~@Ap@Af@?Z@`@?l@@j@Ah@E\\\\AD?b@Az@CrBCjAAP?fBCLAz@A~@A|@CjBA`AApAClAAbBA|@AtACbBCnCCnEEbA@`@A\\\\Az@G`@C`@ArAAH?dDEdBCv@A\\\\?~@A|@?`AB~@Dv@Fv@Jz@L|@N~@V~@X|@Xx@^|@^j@ZbExBpDpBlE~BfCtAh@Xb@VFBrBfAvFzCtBhAzJnFTJb@VpDnBpEhChGlDhCzAfCzAjBhAVLjAr@~D`ChEfCp@d@XLnC~ALFVPjBbAlF|Cx@f@d@ZxA|@dC|Af@ZHFTLz@`@NH|ItFZPDBLHxBpA|BpAh@TlAd@rAf@`@J\\\\FnAP|@Jx@Hv@FL@nADbA?^@V?`@C`@EZEFAtBUbCa@b@Gl@KlBa@~@Qb@I^GnAWf@IVEb@GLATEf@CZAZCj@?l@AnAB`@@N@P@x@Hn@JP@VHv@PhA\\\\t@TXJvE`BRHb@L~@\\\\|Bv@|Bt@~@Tj@PZLj@LnAVpB^~Bd@lE|@nCp@z@RbDp@dE|@tJnB`VdFj@L`@JlAV~Dz@nE`AnCv@RFNDt@Zr@VVLhB|@LFd@Xf@ZhAx@|ApAdAdAbAhAdAvA`@h@`@l@n@`Al@dA\\\\n@\\\\p@x@bBhAfCVh@?@vCxGb@`ATh@f@hA~BjFTn@|DvIVj@j@nAvHpPrCfG`@z@DHf@fALVzCnGVh@b@~@bCjFTh@Vj@h@jAtChGj@pArCbGb@|@P^fFxKh@hAXn@zDfINXjDxHdAvBJV|C~GfEhJZl@b@|@~ErLbAjC|AfEt@|Bz@nC|CnKf@bBz@xCzDfNPn@`AjDnGpT`GzSRp@p@|Bz@xCHVX`AhA|D^jAj@bBn@fBzAtDrBfEzAnCtB`DHJx@lAt@~@hAvAfAnAXZHJHHxBjCv@|@Z\\\\l@r@fApArAbBnAfBFFDHb@p@@BRZFFLPjBtCjFfJXf@t@zAzA~C|CtGb@z@pAfCbD`HvBpEpD`ItDnHdEhI\\\\x@HNLZNXzLrWVh@~AhDvHhPhHxOXj@\\\\p@nBtEzIhQFNjCdGzCfHTf@HPr@`Bh@`AtArCpBfEfChF|CnG@BVh@hGjMpCzFXj@Vh@dBlDpArCf@hAb@hAh@|Ad@tAf@hBb@fB`@hBZ`B@DXjBVjBPzAP~AJnALhBJdBVtEJfBJbB@\\\\TpEVzENfCDb@PxBR`BTdBTpAVpAXrA^rAZfAb@nA`@jAn@vAt@xAd@~@Xf@HNnAvBzAlCLRJP~CrFPXXf@v@vAn@fAb@t@rC`FnAzBhAnBlF~IfApBrAbClA|BxArC`AnB^z@~AlDtA|Cz@lBb@bATl@`@z@|AxDjBbFFRlAfDnAtDtAhEh@hBBJNb@`@pAhAdExBxHbAdElAdFH^DRdAvElBtIDPzAxGdA~EpIha@xHta@|I`f@lEzUPx@Nv@~DzSnCjOrCbOhA|FnCfM~@fEbA`EbAbEnBdH~A~FxAlFbB~EVx@t@`CzBpHrBzGRn@xCnJtBnGL`@xB~GpFzQrBpHt@zCv@bDlBfIxBrKx@pE^zBdBzJh@bDTlBPdAZnB\\\\dCpBlPLz@l@tFz@rHP|AR|At@nG~@bIHt@Jv@ZrClAvK`BrNpB~PfA`KTzBDXDZTlBHh@@J\\\\pCNbAh@xEb@~D^pCf@pEFb@BPHt@zA~LdBrOf@nEt@rGXvBZlCj@vEp@zFr@nFz@hFhA|Gx@zDlAnFd@dBNp@Pp@ZfA|A|ElAtDRl@L`@xBzFzApDjAjCN\\\\jC`Fh@fAb@t@dCjEdCtD`CbD~FbHpHtGxAjAjHpFpBlA^T`@TlExBtAl@zFzBdE|AhEhBxAf@zAf@vAj@bA^`A^^JpAd@rBt@vBv@pBv@vAj@hAb@zAh@xAj@dG~BnFtBtAf@vAj@`C|@lCdAzAj@jAb@`A^HBhAb@ZLb@Ph@RtAh@lAb@jAd@|@\\\\VJHBz@\\\\bA^hAb@jAb@jAb@`A^z@\\\\bA\\\\`A^jAd@^Lx@ZfA`@dA`@h@Rf@RZLVJj@Td@Pd@PZLHBl@Vd@NHD`@N?@t@Zj@VZLj@X~@b@jAr@fAp@~@n@zAjAzArAbA`At@x@^b@~AnBtCdEJLvAzBfAlBpCvGr@bBx@dCxBzH~@zDn@fChBhHtBrHlAdDpAvCpA|BlAtBxB|CZZ\\\\^|A`B~CjCtAnAzApA@BxCtC\\\\\\\\dArAhAfAnBbCNTpAfBV\\\\rApBlBtCxDbGzBjDrDvFdH`LlAjBb@p@d@p@vAvBhAdBl@~@rFpIXd@Zd@bHrKxEhHv@tAzEfHXd@RXf@x@f@x@|@rA`C|CzAdB^\\\\fBdBxD~BxAv@jBx@nBh@bAP`@H@?b@D~@JdBJ~A?vBI|Dc@F?|@[jA_@fDuA|CoB`@]fCwBlByBpBoChB_D|@iBpA}Cd@kAj@yAhAyCzDeKHUJUj@yAvBuFtAuCtAmCjB_DnBqCrCeDz@}@z@{@fA_AhAy@vAcApAy@bEmBbCy@\\\\KpAYZGPCnF}@d@AtAEtBGh@@n@@t@BX@l@Dn@Fx@Jv@LbAR~Bj@rBn@~@^^PjAd@fAb@VJb@RNFPFbGdCzF~BhGfCJFHDTJB@`@R~BbAvDdBhCrAx@d@LFd@VJFv@f@n@`@l@d@pAx@`Av@PLjFhE@BjC~BJJ\\\\^jAhAlAjA\\\\\\\\jGdGpGlGfI|HrAvApCpCxAtAhFhF`C~B@@^\\\\\\\\Z|AvA\\\\Zl@j@nAfAdAv@~AdAzAdAd@TtBdAjAf@PH|Bx@`FpAdBZbAPvANfFT|@Ah@?t@?n@@bAAt@Av@?ZC|GEl@AdGGtGI~ACrG@~BH|AJ|@DdBXfATzBf@`Cx@lDxAXJfDrB~BbBZRlAhAdAbAXVhAhAz@`A|DzEZ\\\\r@x@fAvAJLnBdCvCzDvDpEPRbBtBfBdCpAnBrAhCrChGrAvDrAlEBLtAzFz@tEr@~Ep@xHVzDLpCDt@JvF?xHSnLKxHKvGU~PG~CBnD@v@?d@H|CNjEd@|Gl@pFv@~FbA~EHZdAbEHXdBlF@Fh@|AJRxAdDhBlDxAbCXf@pBhCd@l@vAzADDx@r@PNtBpBBBZV^X^XrB`BhA|@^Zf@^`ChBbCrB^XHFf@b@|EbE`EjC^V|@j@p@\\\\|@Vr@\\\\dC~@|Bp@|Bf@zB^b@FN@PBnGZnBEzCS|@EtGk@zCWtFg@hCUr@GNCPA~BQVCz@Kx@IlBQlBQrCWlDShCItEHjCPfBRx@NvA`@nATZJNBRFxBr@~Ap@hBp@rDpA~Bz@tCdARH\\\\JtAf@`C|@`EpALFLDbAd@vDtAl@PZLVJNH|@`@`@Vb@\\\\BB\\\\NjAp@n@\\\\pC`BxCnB~C~B`BvANJdCxB`B~AnAlAtC`Dv@z@~BtCTVPTPV|@lAxAzBlCbEtA|B\\\\n@`BtCdAtBxAvCJR|@pB|AlD\\\\z@z@tBxA|DbAvCtAhEf@|AfBnGz@bDj@dCf@rBlAzFbAjFnAnHz@`GNdAvCpRJt@Jl@XnBh@pDTvA|@hFt@vE@Dz@bG|@hGBJVzARhAF`@Ld@P`ALr@xAtHd@~ARv@~AxFrBfG|AxDbD~GXf@fAnBb@t@bCnDf@t@LR\\\\b@hA|A|EtFHJzDfDfDnCxAjA|@r@`@\\\\tCzBj@`@tDtCNNtCtBbF`E|ArA`D~CxA|AzBhCvAdBb@l@p@~@pPdT`FlGxHxJ`GtHZ`@rStWtAdBv@dAZ`@vIzKx@bAzCzDpJ~LvGzHpApAl@l@x@t@tAhA~CxBNH^Tp@^|@d@nAh@^Pj@Tp@V~@XtCx@lCl@~Bf@dB^dF~@|HbBhB^b@H~@R|EbAvCp@fGjA~K`Ct@NhE~@H@HBbDr@z@RzCj@b@JH@ND`@HJ@F@nB\\\\fB`@n@NhCj@b@HVFB@b@HVFb@HjAVNDhBd@NBPDB@hA`@hBr@LB`@T~A`Ar@h@fAz@z@v@z@x@dAnAx@dAr@`Ah@x@d@x@@DT`@Xf@FJt@`Bt@jBTj@Tl@BH?BDJ\\\\dABHDNRn@Lb@Rv@DTh@`CVtA\\\\fBTdBR`BRpBPzBN|BJdC^xJd@nOf@dPBj@Dv@v@|R@X@V@Tl@jODz@Bp@JnBPxBPbB@PVlBh@zCJ^Rz@b@bBh@bBt@rBf@fAl@jA^r@T`@\\\\h@DDtApB~@lAdAxA|@fAtBrCpA`Bn@v@|CbErCrDDFTZhAxAhAxAr@z@V\\\\xAjBl@x@zBvC|B~CvBvC~@lA\\\\d@f@p@tAjBX\\\\fAtAx@dArAjBv@bADDVZd@n@p@|@dArAfAxAz@fAz@jAPTbApAv@bAz@hAjBbCbB|B|@jAX^@Bx@dAz@dAxAjBnDpE`C~CfDfExDbFtCxD~@pANPJNz@hABDr@x@lAzAlBxB|BbDZb@DHzCxD\\\\d@dCbDn@x@jBdC|AnBZb@d@j@RXNPpCvDDDhC|CjA~Aj@p@hAxAtAfBX\\\\RXpA`BLRbAnAr@|@hA`Bh@r@RTv@fA|AnBdAtAr@z@h@r@n@|@DDFJfIhK\\\\`@xAlBTXRXPNRZZb@HJRTdAtAfB|BhAxAFFZb@bArAbBvBlA`Bd@l@Z`@RVHHfAtAdB~BV\\\\v@dA\\\\`@nBdC`BzBbEhFlEzFvGhIfAtAX^~AvB`@n@HHPPvH|J\\\\`@Z`@|ArBV`@v@`ArExFjF~Gd@n@`BvBbArAfBvBtD`FTZ\\\\`@hBzBd@l@|CbEzAlBlA|AtBlC|@lAr@|@vCtDpBhCJNpE`GfB|BnA~A~BzCVVt@dANPv@bAdC~CpBtC^`@JJf@p@JNHJvAlBf@n@\\\\`@VZj@n@l@p@bA~@lAhAXT|BzAfAl@NHvAl@^PVLj@RNFRFb@Nx@T|Ct@xAZdARb@JdARb@H|AZdATb@HxBb@d@HB?b@HxFjAtA^pDt@tAXb@H`@Jz@RzB`@vQtDhB^pDx@dARnDt@h@N|Dt@jBd@fATb@HdARb@HfCf@D@`@JtCl@TD`GjAbA\\\\r@PXHJ@j@JbAPlEj@@AvAR`AH\\\\@l@Bh@@n@?h@A@Af@CrBOj@Gl@Mh@Kj@Md@Oj@SNG`@SNGNI@?HGVKDCZMDCVKb@Wd@Q|@k@xAcAj@_@FEHG^W`@WJGHGTKTMJGJGr@e@PM~A_A`@UDAZQ^UDCz@e@rAm@|@]tBo@t@S^G\\\\K`@Gt@KrAMp@E~@A@?vCDvALXDj@HdATlAZRFNDn@Tt@ZB?x@b@f@V^Tr@b@z@n@PLjAbA~A|AbBnBhB~BhCvDFHZb@|DfGhBnCZd@pAnBXd@HJv@lANTfA`BHJn@~@v@fADF\\\\`@FJRT\\\\`@LP|@~@pAlApAbAd@ZjG~CdATdAVxD~@XFdIjBlHzAVFhLbCbDp@b@JzFlA~Bf@B?DA\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 51.90017049999999,\n" +
                "                        \"lng\" : 20.8598399\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"24.8 km\",\n" +
                "                        \"value\" : 24752\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"21 mins\",\n" +
                "                        \"value\" : 1238\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.3194271,\n" +
                "                        \"lng\" : 20.036596\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eDK7\\u003c/b\\u003e\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"{hesHadezB~I`BdAPRDpDv@h@JpB^dAPb@HvFnA`@HbDt@l@NTD~@RJJzCv@v@PtAXvAJ^HH@D?V?`@FB?|@NHH~A`@pAZd@JlCb@JCfCf@ZFdCf@J@tCj@XDb@JfGlAt@NhB`@dDt@H@vCl@VFhCh@jAVlCj@hB\\\\dATb@Jb@HvNxCvDr@NBlB`@|@R`AVhB^bA^j@\\\\r@r@DD|@lArBzCdAfBtAzB|@tAbM~QhAfB|@pAXb@h@v@LPnAnBZb@bAzA~@tAPX`@l@BR@@t@jApAjB?@rAnB^l@^n@Xn@JRHVHVL^@HHZLp@Hf@BVD^Df@Bl@@P?T@P?|@Ad@?l@ErAGxAItAIbAEbA?B@RGx@Ep@?B?@KxACREt@?@C`@I~ACx@Cr@Cr@A|@AhAA`A?l@?^@b@@p@B`@Bb@D`@B`@BTBRHl@@DDb@DPBPF^FXJf@Lb@BLFNBJFNPb@HNHPLTLRNRV\\\\PTVXTVRPXVBBXTDBdAv@hBnAlCfBnBpA`@VPJpD`CdIlF^VNJrFrDfIlFn@d@zJfGpBpAvE~Cn@d@LFd@ZxA|@d@XJHhC`Bj@b@n@^DDr@^DBn@b@|@^\\\\Lz@Zb@N~@\\\\B@lDvAn@XpCfAjCdAt@ZpAn@`@R`@Rd@TfDjB^Th@X|ChBFBnF`D`@T^T`@TdDlBFNdC|AdEdCb@V~@h@@@^T`BbA`@V`@V~AbAbAl@~@l@^TvBtAnBlAnCdBrAz@lAl@FE\\\\RpAr@p@`@\\\\T@@pAt@f@XHF`@Tf@XXN`@TDBt@`@h@b@ZVPNPNj@p@FLNTHNLVJRTh@@Bd@vA?@^lAnB~GnArEL`@DLPj@?BTl@Vt@`@fAz@|AJLv@|@\\\\d@TZTXx@jATZHJFHVRz@rAvCpE^p@JRLRHRLTLXPj@V|@\\\\|APp@bApCZp@X^bIdJ\\\\`@Z^vNlPlAlAjBlA^V`@TdIjFbDtBFZ|A|@d@\\\\ZT@B@@XXJLb@j@z@xArAfCZf@~BhEJBXf@hAxBXf@Xf@Vf@p@pA|BhEXf@p@nAXf@Vf@jAxBTb@JVpCfFjDrGvEzIzKtSfCxEnEfId@z@tBbEr@nBTx@Px@Hp@Jv@PhBZ|E@f@@V@n@@zAChAKjAI~@Mz@YhBAHIj@c@nCu@nDw@lDe@jBAFQn@Qp@A@]`BAF[jBIjAEjAA~CB|@BdAJvAL|AN|ALlAVfBT`AH\\\\r@|C|@fEFX`@|Bb@rCf@|CHh@@HJt@XfBJt@p@rEJr@XhBJt@p@pELt@Jr@Jt@Jr@Lt@b@|CXfB`@vBh@bBN`@Z`ANb@DF\\\\l@DHb@n@f@h@BBDFFDfAbA`@ZpCrB^Zl@d@pB~A^XJHpB~AHFTPXVNP^b@j@x@PVT`@^z@^bA|@fCz@hC^jAZ|@|@jCJZzCvIATTl@Zn@T^DJ\\\\f@z@dA`@Z`@ZpAl@p@Vx@Pz@Fl@BFAlA@R@rBA~CBnEHpBFTAp@?b@?rCDb@?b@@nCBfJJjB@|FR?@@B?B@B@@@B@@@@@@@@@@@@B?@@@?@?B?@?@?@A@?@?@A@A@A@A@A@C@A?C@A@CzBMfE@T@b@?~@@nAD`@@jBJd@FPB~@HZD^FB?f@B`@BjD\\\\lAJj@FTD`@?B@\\\\D^Fl@Hr@JD?\\\\D^Df@Dj@@`@B\\\\?~B@jDCV?dGAjAAj@?XAN?P?|A?tEAHFlB@|@@lB?P?bA?fB?^?ZBj@@REb@Fh@H^Hl@Jf@Hl@Lh@Lp@Rp@Rr@V`A\\\\n@Zr@\\\\v@b@l@^d@X^XBBz@l@^\\\\^Z|@x@T\\\\DD~@fAnAzA~@tAZf@nBhDnA|BDDPVl@hAFJj@fAh@|@Xd@JPZd@X`@^`@RRPPZZf@d@b@\\\\JH\\\\Xd@XDDv@`@ZL\\\\Np@Vn@Rt@Th@LF@lANb@Dj@Dt@DV@`CJxBF|@D~DLX@tDNfEJxBJz@B|BA`BDbF\\\\pADzBJjELJ@b@@xFRvBF~AH|@Bd@@b@Bb@@lBFX@R@N@@?~FRl@?vADn@BxERP?zAFJ?f@Av@Kh@S@Af@UzAiAFEx@s@x@o@`A{@n@e@nAaAJK^Yb@WPIJGTIJE^M~Bu@b@M`@MNEJ?dA[`@MDC\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.4950178,\n" +
                "                        \"lng\" : 20.1838456\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"32.4 km\",\n" +
                "                        \"value\" : 32441\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"37 mins\",\n" +
                "                        \"value\" : 2237\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0863115,\n" +
                "                        \"lng\" : 19.9547424\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e to stay on \\u003cb\\u003eDK7\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-slight-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"m_crHwkhyBNWX_@LSTm@L_@d@uAXeAX}AR{@J]PWV]ROZM^KJ?D?V@LBTDnAp@l@Zt@`@bBz@b@LNDX@j@KbBe@~B}@B?\\\\[R_@Pm@\\\\_AT]ROPCR?HBNBPLXTt@r@ZPPBNAT@BAlA]n@Sd@O^Mh@MBA`@KZATAR?L?`@DXDb@LB@d@RlBt@j@PxE|AdDdAdFdBvFpBn@RzItCr@V|DvAnFjBfBl@fFdBzEzAfA^N@T@P?XEVITITKXQ^WZUb@c@v@s@PG|AaBtA{Av@s@n@m@^]NOLK\\\\]zAuAFIDIHWRWBE\\\\o@Zs@DKPo@FSH]Lo@@ALs@?CHq@@IFcADiBFuCBw@?CDsBJgBH]@G@IHs@Fq@Fe@F_@@CLaAFc@F[Pw@Jc@Nk@Lg@BKNg@BGPg@@EJYTi@\\\\}@\\\\u@\\\\s@BOLSJQf@u@NQv@cAfM_PjNqQDE`C{Cp@{@bEkFV[xHyJ~FsH\\\\c@Za@Za@Za@x@cAZa@tAgB~@mA~AqBtDwEtD{Er@{@rDyEpBiCn@u@BEXYf@g@vCuCtAuAjBgBlCiCnAkApCoClBmBn@o@vCsCpAoAd@c@pGmG`BaBvBuBbAaAf@i@|A{AhFgFlOcOvCuC|@{@nHiHzAyA~HwHzByBvCsCJKfAeAFGtDqDLO|@aANOPC`B{@jEDd@Kx@D^@bDJ`CL`@FTDTJRNRPRTJTJV?@JVDVBFHj@JtALpBBRB`@BRDZLf@L^HRJPV^j@f@hBh@`Bf@h@^d@N~A`@bEbAR?tBl@fD~@x@T~C|@PH\\\\NnBx@THdA`@PFB@B@L?BABAFC^c@HMFIFC@ANIN?XDZFb@HfAR`@HhB\\\\j@Lh@LrATlCf@rCf@\\\\HLDLFLJLNJLHLFPHR@FDNF\\\\@BDf@@LLnBDt@Fv@R~Cx@|LTtENrARvAJr@f@~BTdAh@pBBHx@jDj@hCh@|Bz@dDxAjGHZF\\\\dArETz@j@`Cz@tDvCdM~@vDd@pBX`AxAnGPp@`AdEPp@Np@rAvFNp@rAxFrAxFvDbPbAdENp@vFxUd@pBbAfENp@h@zBpArFlAbFJf@@BPt@nClLrA|Fj@vBZ~@N`@JVf@jAh@bApAnBnBlC|@lAdJbMxHhKZb@bLnOlAbB~F~HnBrCj@t@v@dAbAtAHJV`@hAvATZ`QpUtD`FbDrEtBpCV\\\\xErGjAvAX^RTVR`Ap@fBbAPL`@V`BdA^V@@|FpDfAp@@?~AfAt@f@tCnBHFt@f@`@VbDxBz@h@|@j@|@l@\\\\VTNzA`AtBtAbDtBb@ZbAl@JHr@b@^V^V|AbAvA`Aj@b@`@\\\\lApAnCxC|CpDhCtCJLdE|E~AhBtA~Af@f@fCxC\\\\^\\\\^\\\\\\\\dAhAz@dAz@dA@@j@p@LPZ^x@bA|@fAlAvAlAxAlCtCb@h@|BfCTVbCtCj@n@pB|Bd@d@v@l@bAt@z@n@^XbFrD\\\\V~C~BzC|BDB|ApARRLLLPRVh@|@Xj@JVRl@\\\\fATv@VjAT~@r@rDHx@FnCDbBD|B@z@D|@Dl@Fr@Lz@Lr@H^FRLd@Xp@Zp@T`@\\\\f@NRLLRNZXTJTLXNXHRDFBTBRDVBT?X?P@N?P?F?\\\\@jB@|@?n@?jA@z@?z@@Z@X?`@?TCPAXG`@GvAUbASBAvAWx@QVETERElAUzAYHC^IdBYnASjASbASbAQ^GTE^InAS~AY|@Ot@Of@I`@IR@h@Gr@MRE|@QREXE~AYt@Op@Mx@OPCHCHILCh@KNEb@Kd@S`@Q\\\\Ob@WZO~CoB^UHE\\\\WTIlAw@z@g@LIl@_@n@a@r@e@ZOh@_@d@Yb@UVIREVA`@@^?f@@p@?z@@jABfBBN?B?L@bA@V@l@@T@R@PDTDXNRLPLZ\\\\h@n@r@dAPTb@n@Xb@`@l@Xj@Xn@Tn@Rl@Nj@Lp@Jt@Ht@Ft@Bx@B|@BhBBnB@rB@jA@d@@h@BZJn@Lf@L\\\\PZTVPRPNVHl@HRAREVMRKRQb@k@l@u@n@}@l@s@TWNITKJCN?PAPBJBVNTRR\\\\JRLXZ~@V|@Hd@Ff@Bf@@f@Aj@Ed@Kj@Mr@Oj@Sv@Mb@GXI`@EXE`@AZA`@?N?P@VBRLp@Ld@Rd@LTRTXRVLb@NDFnBp@^JTFPDh@Pj@Ph@Rt@TrA^xA^lA^HC`AZHBbDbAnA^NDvA`@HBBH@?@@jA^|@Xp@RVFt@Rd@Nh@RbAZh@Nh@PbAX`AZFBv@THBt@T|@Xb@NZH\\\\J^J\\\\DVBH@PAd@IZK`@Sf@Ur@[TKz@a@p@[h@Wp@]j@Wr@]d@Qh@O^EVCd@DP@H@L?dAHz@Dv@Hh@D`BJb@Bv@HT@^Bb@Dl@Bb@Bj@B`@Dj@Bd@F|@Fn@Dv@Fz@FN@d@D`AF`CRH?pAHP@~@FJ?B?@?BCVFpFZP@ZBZDnALp@Bp@Dn@DL@\\\\@d@DN@T@\\\\?^AZC^En@W`@Of@Gr@Mj@C^AZC`@@X@h@AZJLBNHPFTHb@P@@d@P^TXPZPNHVH@@p@Pz@R~@LVBhAF`DVbEXpBNdAHbAFn@FJ@zAVpLdCxAZpAXVFbAT~A\\\\VFv@PlAVD@dB\\\\fB^vAZtAZj@JTFt@HtAXjAVtAX`@H@@dARb@JtAZp@N`B^tAVx@PNDp@NxAX|@RjDn@\\\\JRBz@Tl@J`ARTDrAVt@NhARvAZbAVJ@zCl@LDRDRJ`@^PLfAfAVTPPXZNNx@z@NLLP|@~@t@v@PPd@f@Z^tAxAJJ\\\\Zl@r@`@d@fAfAPPJXFJJPVXLPb@d@NPTTPTTXdAbA\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.3194271,\n" +
                "                        \"lng\" : 20.036596\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"1.2 km\",\n" +
                "                        \"value\" : 1229\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"2 mins\",\n" +
                "                        \"value\" : 143\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0763076,\n" +
                "                        \"lng\" : 19.9474294\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Continue straight onto \\u003cb\\u003ealeja 29 Listopada\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"straight\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"mnupHclxxBZVLJLJ^Zj@`@v@l@LHj@^~AfAPJr@d@d@XXRB@|@n@hBlAh@Zz@f@VN^Rh@X~A|@bAp@|@l@x@f@ZRRLXPv@f@h@Z`@Zf@Zz@n@f@ZXTFBd@Z|@d@bDtBjAv@pAdA`Ar@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.0863115,\n" +
                "                        \"lng\" : 19.9547424\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"2.7 km\",\n" +
                "                        \"value\" : 2660\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"5 mins\",\n" +
                "                        \"value\" : 276\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.058043,\n" +
                "                        \"lng\" : 19.9586896\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eII obwodnica\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"}ospHm~vxBNXFLBF@DBH@D@BFf@@bD?b@?d@@H?D?D@DBF@BBBBBBBB@D?D?TC~AWnB]TGVIVKTKn@[XQLI\\\\Yn@m@d@e@FIx@{@NO`@c@f@k@h@i@p@s@PSd@g@^a@`@c@f@i@BCHGNOPKRK@AHETGRCRCj@EVATCt@Ed@E\\\\I\\\\KDC@?JGTORQHKHKT[Ra@Pc@Pi@BMRq@^{ANq@Ls@ViBFe@d@iE|@aIXuCh@oELiARs@L]NYNU@?FMFGFERKl@UfAe@fB}@f@WXOd@YPKRM`@URKTGNGPCjB_@lDs@b@Ib@GVAVAVAT?T@T@T@RDRBTF\\\\FfCn@bEdAj@L@@|Bp@VHTLFBNJTR\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.0763076,\n" +
                "                        \"lng\" : 19.9474294\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.4 km\",\n" +
                "                        \"value\" : 420\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 46\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0545104,\n" +
                "                        \"lng\" : 19.9595193\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"At \\u003cb\\u003eRondo Grzegórzeckie\\u003c/b\\u003e, take the \\u003cb\\u003e2nd\\u003c/b\\u003e exit onto \\u003cb\\u003eII obwodnica\\u003c/b\\u003e/\\u003cwbr/\\u003e\\u003cb\\u003eKotlarska\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"roundabout-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"w}opHydyxBVf@\\\\^HBH@H?JA@?FAFCFEFIDE@C@CBEDIDKHKDE@AHEHCt@EHAfAGl@CVCTG@AhCw@x@WtAa@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.058043,\n" +
                "                        \"lng\" : 19.9586896\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.1 km\",\n" +
                "                        \"value\" : 120\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 14\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0534544,\n" +
                "                        \"lng\" : 19.9594705\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Take the exit toward \\u003cb\\u003ePodgórska\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"ramp-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"ugopH_jyxBx@MFAZ?ZA^?NBh@V\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.0545104,\n" +
                "                        \"lng\" : 19.9595193\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"1.1 km\",\n" +
                "                        \"value\" : 1096\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"3 mins\",\n" +
                "                        \"value\" : 168\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.04741,\n" +
                "                        \"lng\" : 19.9476126\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003ePodgórska\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"aaopHuiyxBPXPXLP@FHb@F^BH@FDPHb@jAxFh@zBJd@Ld@Ld@Vx@f@hA\\\\p@RT~@`Ap@d@^h@Zf@DFd@z@\\\\v@R`@Rj@Tp@Vr@LTFJJTVj@l@rAXn@Vh@fAfCHPN^p@xAv@dB`@~@^x@f@pAXv@Rz@\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.0534544,\n" +
                "                        \"lng\" : 19.9594705\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.3 km\",\n" +
                "                        \"value\" : 274\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 51\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0496916,\n" +
                "                        \"lng\" : 19.9461756\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eGazowa\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"i{mpHq_wxBw@b@}@d@kDnBeDbB\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.04741,\n" +
                "                        \"lng\" : 19.9476126\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"0.1 km\",\n" +
                "                        \"value\" : 126\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 28\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0493149,\n" +
                "                        \"lng\" : 19.9445066\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e onto \\u003cb\\u003eŚwiętego Wawrzyńca\\u003c/b\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-left\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"qinpHsvvxBjAlI\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.0496916,\n" +
                "                        \"lng\" : 19.9461756\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                     \"distance\" : {\n" +
                "                        \"text\" : \"39 m\",\n" +
                "                        \"value\" : 39\n" +
                "                     },\n" +
                "                     \"duration\" : {\n" +
                "                        \"text\" : \"1 min\",\n" +
                "                        \"value\" : 10\n" +
                "                     },\n" +
                "                     \"end_location\" : {\n" +
                "                        \"lat\" : 50.0496509,\n" +
                "                        \"lng\" : 19.9443626\n" +
                "                     },\n" +
                "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e onto \\u003cb\\u003eBożego Ciała\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eDestination will be on the right\\u003c/div\\u003e\",\n" +
                "                     \"maneuver\" : \"turn-right\",\n" +
                "                     \"polyline\" : {\n" +
                "                        \"points\" : \"egnpHelvxBcA\\\\\"\n" +
                "                     },\n" +
                "                     \"start_location\" : {\n" +
                "                        \"lat\" : 50.0493149,\n" +
                "                        \"lng\" : 19.9445066\n" +
                "                     },\n" +
                "                     \"travel_mode\" : \"DRIVING\"\n" +
                "                  }\n" +
                "               ],\n" +
                "               \"traffic_speed_entry\" : [],\n" +
                "               \"via_waypoint\" : []\n" +
                "            }\n" +
                "         ],\n" +
                "         \"overview_polyline\" : {\n" +
                "            \"points\" : \"apy}Hy~g_C~I|w@fWeM~IpB|Gnj@tMfcA~VnwAb]|{A~f@zoA``ChaEbsAdb@rz@p]jmAxz@|r@hbAvt@xk@`l@zWz\\\\Kd_AubAzTaGx^tHfpCbx@ffDj`AhuI|uCjj@xQnVwHnh@uKjeA`|@~U]hbCgo@pnAcYphCao@zb@sTlq@{InVuBhNtGhc@hRzeAjDvt@~X`Zy@lXg\\\\hc@o_Axs@ig@x`EkfAr|FywAnpModEbzBy}@vuA_~AjfBkiBdZaNbZlIr}@xWxiBy_At`EieCzg@eu@~w@q~Azv@mYzkAyvCznA{yC|d@o`@zW}Gt_A`I`jAj]fkAzx@rsAdkAfk@dm@~cBzgAtb@tRh`A|Hz|AnN`pB|[|s@bYnc@tDzoBr`@b{@dL|q@sD~}@cDlp@bDzd@}M~}@nC`~@j`A|sAttBf`AthB`~@`bCdbCdoGxx@tnCbb@xm@~qBz[jlCv~@xwDrIxe@vS`]l_@tt@tkAle@j]lm@{C~y@{~@xj@gWtYrC~h@lf@|s@r`@|hCkGj_@`A`|@dd@p~A|~@|]tItXkE|t@bNztAdZ`ZpW`eA~|Bt|AljEnw@xoAdqBfhEld@`dAzGrv@`Mvh@jc@fv@r{@hkCnhAttFhn@~uBxi@jeErSjfBpZ`gAxc@~l@|Y~Qlv@pYvmA`e@pr@xd@nh@ftApb@pd@l_AhxAhj@zh@bSEnVqOdg@oeAxk@wXz_@tGteAbl@d{@~y@`o@bTty@Edm@n_@`j@vaAtCtsB~VrkAvXbXvn@d]~t@{Cho@^~o@tUh`@nWte@fq@v^hpAtR~oAp]z_A|r@hn@dtCleDbmBxa@vUdGhVxX~R`eCvKvh@l`@ph@v|@`kApaCn}CtjDroEbr@p`@vbBl]~q@fAdc@aUjYd@t[|YzVd_@xbA|\\\\`lA|UlcBf]h|@rlAPdf@lBdd@brAv~@rbCpsAnZjl@tz@ljAj_@|_@hV~c@vl@|vAgH`g@f@|d@zLpt@rIb^lUfRrRhg@zUdJnt@bB|`BrCri@jUza@|c@z}A|Fj`@gApS_M`KoPrOjBx^eIpzAf`@tWsh@fJud@zmBgbClzB{wBtZ`B`EfQpo@nNnXvGnEri@rr@txCb\\\\xsA``ArrAhu@f_Axc@rYhhArdAz~@p~@|Pds@fvAaOrd@wRlPlAdKdVnCjYtLeHtB`_@le@bRvg@`Gxi@o@ri@rBzvBd]hp@nTda@z`@vp@rb@tBnLrKeBxZ}SzQmu@`R}H|XhA~NdB|ScCpGzVlZjr@cIfQcA\\\\\"\n" +
                "         },\n" +
                "         \"summary\" : \"S7 and DK7\",\n" +
                "         \"warnings\" : [],\n" +
                "         \"waypoint_order\" : []\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }
}
