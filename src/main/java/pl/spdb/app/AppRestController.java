package pl.spdb.app;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.spdb.app.algorithm.WaypointFinder;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.model.route.Routes;

import java.util.Map;

@RestController
public class AppRestController {

    @Autowired
    PlacesService placesService;

    @Autowired
    WaypointFinder waypointFinder;

    @GetMapping("/dev/test") //MOCKED DISTANCE MATRIX API
    public @ResponseBody String testing() {
        String json = getRoute();
        Gson gson = new Gson();
        Routes routes = gson.fromJson(json, Routes.class);
        return gson.toJson(waypointFinder.findWaypoints(routes));
    }

    @GetMapping("/dev/mocked_result")
    public @ResponseBody String testing2() {
        return getFinalResult();
    }

    @GetMapping("/api/categories")
    public ResponseEntity<Map<String, String>> categories() {
        return ResponseEntity.ok(placesService.getCategories());
    }

    private String getRoute() {
        return "{\n" +
                "  \"geocoded_waypoints\": [\n" +
                "    {\n" +
                "      \"geocoder_status\": \"OK\",\n" +
                "      \"place_id\": \"ChIJAZ-GmmbMHkcR_NPqiCq-8HI\",\n" +
                "      \"types\": [\n" +
                "        \"locality\",\n" +
                "        \"political\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"geocoder_status\": \"OK\",\n" +
                "      \"place_id\": \"ChIJr1ZFdSTLG0cRrNshm2OufMs\",\n" +
                "      \"types\": [\n" +
                "        \"locality\",\n" +
                "        \"political\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"routes\": [\n" +
                "    {\n" +
                "      \"bounds\": {\n" +
                "        \"northeast\": {\n" +
                "          \"lat\": 52.2320088,\n" +
                "          \"lng\": 21.0173684\n" +
                "        },\n" +
                "        \"southwest\": {\n" +
                "          \"lat\": 51.7592212,\n" +
                "          \"lng\": 19.4559892\n" +
                "        }\n" +
                "      },\n" +
                "      \"copyrights\": \"Map data ©2020 Google\",\n" +
                "      \"legs\": [\n" +
                "        {\n" +
                "          \"distance\": {\n" +
                "            \"text\": \"138 km\",\n" +
                "            \"value\": 137666\n" +
                "          },\n" +
                "          \"duration\": {\n" +
                "            \"text\": \"2 hours 29 mins\",\n" +
                "            \"value\": 8934\n" +
                "          },\n" +
                "          \"end_address\": \"Łódź, Poland\",\n" +
                "          \"end_location\": {\n" +
                "            \"lat\": 51.7592212,\n" +
                "            \"lng\": 19.4559892\n" +
                "          },\n" +
                "          \"start_address\": \"Warsaw, Poland\",\n" +
                "          \"start_location\": {\n" +
                "            \"lat\": 52.2291168,\n" +
                "            \"lng\": 21.015462\n" +
                "          },\n" +
                "          \"steps\": [\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"74 m\",\n" +
                "                \"value\": 74\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 30\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2284848,\n" +
                "                \"lng\": 21.0156871\n" +
                "              },\n" +
                "              \"html_instructions\": \"Head <b>south</b> toward <b>Żurawia</b>\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"__x}Hsqg_C`@SZMBABAB@@?@@BB@B@?@?f@S\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2291168,\n" +
                "                \"lng\": 21.015462\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.1 km\",\n" +
                "                \"value\": 120\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 46\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.228821,\n" +
                "                \"lng\": 21.0173684\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>left</b> onto <b>Żurawia</b>\",\n" +
                "              \"maneuver\": \"turn-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"_{w}Hasg_Ca@_DGo@SeBEY\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2284848,\n" +
                "                \"lng\": 21.0156871\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.2 km\",\n" +
                "                \"value\": 224\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 72\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2307453,\n" +
                "                \"lng\": 21.0163943\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>left</b> at the 1st cross street onto <b>Krucza</b>\",\n" +
                "              \"maneuver\": \"turn-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"c}w}Hq}g_CYHi@TgA`@WLYNsCjAo@V\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.228821,\n" +
                "                \"lng\": 21.0173684\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.3 km\",\n" +
                "                \"value\": 328\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 60\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2301315,\n" +
                "                \"lng\": 21.0119668\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>left</b> at the 2nd cross street onto <b>al. Jerozolimskie</b>/<wbr/><b>DW631</b>\",\n" +
                "              \"maneuver\": \"turn-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"eix}Hmwg_C[JJ|@\\\\~BXtBn@fFDVFp@Hx@Dn@BZ@N?d@?R?V?X\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2307453,\n" +
                "                \"lng\": 21.0163943\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.5 km\",\n" +
                "                \"value\": 505\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 76\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2285204,\n" +
                "                \"lng\": 21.0051058\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>1st</b> exit and stay on <b>al. Jerozolimskie</b>/<wbr/><b>DW631</b>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"iex}Hy{f_C?PAD@N@N@N@JBJBHBH@D@BBFHHJb@JVRp@Nn@F\\\\F\\\\N`Al@~Ef@~DRfBP|AJ~@L`AVhBJv@Fd@\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2301315,\n" +
                "                \"lng\": 21.0119668\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.2 km\",\n" +
                "                \"value\": 213\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 43\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2279632,\n" +
                "                \"lng\": 21.0021163\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue straight to stay on <b>al. Jerozolimskie</b>/<wbr/><b>DW631</b>\",\n" +
                "              \"maneuver\": \"straight\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"g{w}H}pe_CXbCb@lDDV@RBP@L?B?PBPHp@T|B\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2285204,\n" +
                "                \"lng\": 21.0051058\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.5 km\",\n" +
                "                \"value\": 458\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 67\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2265401,\n" +
                "                \"lng\": 20.9958145\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>1st</b> exit and stay on <b>al. Jerozolimskie</b>/<wbr/><b>DW631</b>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"www}Hg~d_C@PBN@B@L@B@H@DBNBNBL?@P|@\\\\dBBLHb@Hr@t@vFBVd@nDRzAFj@Hr@p@nF\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2279632,\n" +
                "                \"lng\": 21.0021163\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.6 km\",\n" +
                "                \"value\": 630\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"2 mins\",\n" +
                "                \"value\": 108\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2318387,\n" +
                "                \"lng\": 20.99258\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>right</b> onto <b>Żelazna</b>\",\n" +
                "              \"maneuver\": \"turn-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"{nw}Hyvc_C_Bt@QHwCtASH]^yDpBUJ_FpCgB|@SLGDMFUFIDSH]P{@`@WN\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2265401,\n" +
                "                \"lng\": 20.9958145\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.6 km\",\n" +
                "                \"value\": 574\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"2 mins\",\n" +
                "                \"value\": 95\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2306191,\n" +
                "                \"lng\": 20.9846859\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>left</b> onto <b>Prosta</b>/<wbr/><b>DW719</b>\",\n" +
                "              \"maneuver\": \"turn-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"_px}Hsbc_Ca@VPjAT|AL`An@bFB\\\\B^JdBBh@@L@b@@PBT@PDX^`DBNHl@|@fH?B@FDb@B`@H~ABr@\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2318387,\n" +
                "                \"lng\": 20.99258\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.5 km\",\n" +
                "                \"value\": 537\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 70\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2293754,\n" +
                "                \"lng\": 20.9771224\n" +
                "              },\n" +
                "              \"html_instructions\": \"At <b>rondo Daszyńskiego</b>, take the <b>1st</b> exit and stay on <b>Prosta</b>/<wbr/><b>DW719</b><div style=\\\"font-size:0.9em\\\">Continue to follow DW719</div>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"khx}Hiqa_C@X?T@N@L@H@HFXBNFZLp@Jh@ZdBHb@DTBR@BHl@Fl@@@LjATtBDf@Dh@D`ALtBDz@FjAF`AFxABjA?f@RjB\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2306191,\n" +
                "                \"lng\": 20.9846859\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.2 km\",\n" +
                "                \"value\": 201\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 17\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2291022,\n" +
                "                \"lng\": 20.9742087\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue straight onto <b>Marcina Kasprzaka</b>/<wbr/><b>DW719</b>\",\n" +
                "              \"maneuver\": \"straight\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"s`x}H_b`_CRfBBPF~AFtBF~BFtB\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2293754,\n" +
                "                \"lng\": 20.9771224\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"4.1 km\",\n" +
                "                \"value\": 4084\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"7 mins\",\n" +
                "                \"value\": 437\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.22022,\n" +
                "                \"lng\": 20.9166075\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue straight to stay on <b>Marcina Kasprzaka</b>/<wbr/><b>DW719</b><div style=\\\"font-size:0.9em\\\">Continue to follow DW719</div>\",\n" +
                "              \"maneuver\": \"straight\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"{~w}Hyo__CDdBLlENnFBnAL|E?^@F?BAJ@nA@X?t@BlAJrC@ZBd@@\\\\Bl@?L@J@VD`BFlBFxAXnKJfEJfEBp@@V?X@X?hA@bB@p@?n@BdE?b@@^@d@BrAFdBFl@Db@DZDr@FlAJrCFxCD~BP|G@T@P@JDh@`@rEFn@T`CH~@H`AF~@H`AN`C`AvNv@|LH~@F`A@NJ`Bn@vIPdCN|AJ`ANjAX`BTpANn@VdAJf@Hd@Hf@RjARjA|AdIl@`Dl@`D\\\\dBTfABNj@rCNdAF\\\\F^n@jDd@|CXpBp@nERpAXfBXzBBJBLvAvJnAtINbARdBDXlAjINbAN|@\\\\pBNx@VvA\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2291022,\n" +
                "                \"lng\": 20.9742087\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"1.4 km\",\n" +
                "                \"value\": 1440\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"2 mins\",\n" +
                "                \"value\": 132\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.21652599999999,\n" +
                "                \"lng\": 20.8964564\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue onto <b>Połczyńska</b>\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"kgv}Hygt~BLz@t@dFb@~CRvAF^vBxN\\\\`Cd@zCDd@bAzGzBdOPhAJ~@PtAFr@BXJnADd@JfAFhAFfABj@Bb@PhFBdAD|@@XD|@H`AZpFB`@DlABp@DnA@j@@PBNBZ\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.22022,\n" +
                "                \"lng\": 20.9166075\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"1.6 km\",\n" +
                "                \"value\": 1598\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"2 mins\",\n" +
                "                \"value\": 131\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2165432,\n" +
                "                \"lng\": 20.8730592\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>2nd</b> exit and stay on <b>Połczyńska</b>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"ipu}H{ip~B?@A@?@CB?BA@AB?BAB?B?B?BAB?B@B?B?B@B?B@B?@?@@??@?@@??vA@~A?zAAxAAtD?Z@J@\\\\?L@`@?\\\\CNAX?^?^?Z?zA?d@?X?X?R?f@?ZBzE?nEAvLBlNAdC?n@AhC?dJ@v@?`@?jA?vC?pD@dBAlF?lA?vJ?lA?V?bA\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.21652599999999,\n" +
                "                \"lng\": 20.8964564\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"13.7 km\",\n" +
                "                \"value\": 13714\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"17 mins\",\n" +
                "                \"value\": 1015\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2100737,\n" +
                "                \"lng\": 20.6735202\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue onto <b>Poznańska</b>\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"kpu}Hswk~B?`BA`F?rB?lA@fA?z@DfBHlDNlHVzJ@b@@\\\\@TFfC?F?J@j@@N@n@@B@|@Bd@@\\\\@f@J|D@XD~AH`DB~@D`B?@JnEH`DJrENnFH|DHbDHjCPvKDxCNlGNbHHzB@ZZbJFhBDvABfBDfDFjADbAP~BBb@@FRbCFf@@RTnBBXB^PzBTlD^zD?BHr@N|AHhAR~BTlCN~ABZPtB\\\\rEPpB@RD^Bn@n@zHL|AP|BTbDZ~ENv@p@nHRhCJjAVxCBf@Fn@RxAh@lGN~Ab@fFXpCjArNrAdPH`AA\\\\?HLxAZfDFfA@rABnBE`DCpAEdFCj@G|GEnDEpGAx@APAfB?rDQzQEbDC|AA|BGrEA~@CbDElD?pAE~C?FA~AGtC?^Fp@ARCdDA~@ChC?FGnGIjJAr@MfNAjBCxCCtAIlBAlAAnAEdEGfGG|FCxCA|AC`FBj@EvDAtACn@ElBEhEAxBExCEpF?B?JF\\\\Cj@AdBCzDGxMErDCrFEhDCvA?VAxBIb@?Z?~CAvAAnAAvA?~@CvD@ZClC?dCI~JAdBEbHE|@EvBA`DAv@?XE`J?J?F@j@KrLA`BCjGKfAC`D?HEpGC`CA`DCbCCzFGvH?XClC?FClDCrDF~@ClCInMAvACbDCvDAbBCzCCpGI|HC|FEvFKvLCZ?p@MxIE`KDR?`@M~RCvE?DC~EAl@CpEGhIE`G?L?@?@Eb@CvB?lAAz@A`BAzD?P@L@J?n@CxAArCCbG?HCfA?d@Bd@BbAHx@FhAD`@Bd@j@rKd@~Hb@|G?DDr@@L@HFb@Dl@J`BZhF`@nH?^BVFtAV~DBl@\\\\`GJrBDf@Bp@BXf@jIHrAZdFFt@T|DL|BNbCXnEB^FnAJzADr@\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2165432,\n" +
                "                \"lng\": 20.8730592\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"34.1 km\",\n" +
                "                \"value\": 34145\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"33 mins\",\n" +
                "                \"value\": 1956\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.2190945,\n" +
                "                \"lng\": 20.2043716\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue onto <b>DK92</b>\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"}gt}Hoxd}Bn@bLHlABl@ZlFFfAJtAf@zIBTP~CRlDPlDNzBX`FJzAB^DLNdCNjCLnA?R?NV`EBr@Z~DDTPvA`@fC@D\\\\tBVjBf@pDf@tCdAfHHPLbA|A~J^jCx@vF@N?H|@~FdAdHr@lEl@dDDLjA~Hn@dEdB`LHh@pBlM@DD^V`BRpAXdBF\\\\ZtB@Fz@vF@Hp@fEXfBRpAp@lElApIZpBdBzKNz@@VZpBv@pFDThBnLN`ANdAZrB@Nf@~CHx@F`A@R@L?`BAp@SbNEvCErDEjCIbGG~CE|CAf@ItDMrJCpAAt@Av@CdAGxGAp@CxAAZAR?BAf@GlEGpDCz@GfECxC?H?D?PUbPGlFKnGOnKEfDAf@Ef@GfDAp@CdACnAE`ECzAAp@?P?P?F@LEzBEzBAr@GpEUbNGtDGvDEdDa@jZCFAD?FEhCC`BElB?^EtDEtB?L@H@D]xVADABAF?LQ|KKzGSrNObK?L?HBL]fSEdD?\\\\EdCEf@A\\\\ElBAx@MjICx@@nAGfEKzE?HAFAL?\\\\EvB?RAhBGrBEtCCtB?L?H@JKtGA~AEpBIbB]jHOxEWtFu@bQs@xPADGp@Cz@KtBg@|JcBh`@{@`UCb@GdBYtGQ~CMrCYhHKdCa@nI[zHInC?J?H@JItBg@tLi@hM[xG_A~RETKtBGlBC^w@hRGjAE`AI`B[nH}@~SUtFOfDEpAAJ@Tc@bLErAGzAEtA?BIxAGzAGzAG|AIzAG|AG~AI|AG~AI|AG~AEdAAXI~AG~AALCx@CTG~AG|AG|AI~AGzAC^E|@GxAGzAIxAGxAIzAGxAGxAIzAGzAGzAIzAGlA?NIbB?@?FMp@OdE_@vGKtDAN@N_AfXG^GnAOnCGjAGvAEdAAZC`@C|@G|AG|AG|AGzAAp@?R?Na@pJGtAGtAGtAGvAGvAGvAGtA?BIxAq@bPCn@En@OhDUvEOnDInBKjBAHG~@k@xMCVEnAEx@ATA`@AJA^GnAElAGjAEhAEdA?@GrAAh@Cf@GpAEnAGnAElAEfAG|AAZC\\\\Cz@Ex@Ez@Cv@?FSnDCr@AFARCn@EdAEhA?FE`AAREv@GlAElAGpAEnAGrAGpAGtAGtAGjA?HAPEdAGtAGvACj@Cj@GvAAh@En@GrAG|AEnAAFGxAGvAGxAIxAGxACn@Ex@EnAIzAG|AATE~@GtAE~@E`AC`AEv@?JEp@A^GnAIhBGfBIfBCb@E|@G|AG|ACj@Cp@GrA?FG|ACd@@B?B@DCn@I~AG~AG~AI~AG~AI~AG~AGzAIzAGzAAXEbAGzAIxAGzAG|AEpACDABADAL?DG`AMpCGbBA\\\\En@Al@ATGdAEtAADGzAGxAGlA?XBNEbAAHCf@Ah@Ej@Cl@G|AGzAGzAABGvAGzAGzAGxAE~@AZGzAIxAGxAEhAERCHCX?HCd@GrAGhA?JEfACXCr@ElAGvAIlBEhAIhBIxAG|AI|AG|AI~AGbBARCl@ATAf@C\\\\A\\\\Ct@AHGtAGxAGvAGzAGzAAPAXAN?N@RI~AG~AI~AG~AG~AG~A?@IxAG|AG|AG|AG|AG~AI|AG~AG|AI~AAd@C`@Cj@G|AI|AG|AGzAGzAGzAGzAGxAGzAI|AGzACl@Cn@EbAAXIzAI~AEfAEv@?NGhAEfAEhAI~AG|AG`BG~AI|AG~AG~AI~AG~AG~AI~AEhAEfAGfA?JE|@EfAI~AG|AI|AEbA?FIp@A\\\\U|EEdAEhAKlBEzAI|AG|AG|AG|AEj@?L?LBPAHA^Ez@G|AGzAI|AG~AG|AI`BEfAEfAGhAEhAEfAEhAEn@CFADAF_@lHEnAC^C|@E`AAXE|AAL@H@NEv@G|AGzAGxAIxAExAE|@AHCJAHAH?DKbCEx@El@A`@Cp@EtAGvAGrA?DGrAGvAGlAMxC[~He@vJ?@Bz@AJGrAGtAGrAGrAErAGrAIrAErAGrACh@Cj@GrAGtAIrAEtAAVE~@GtAGtAC`@Cr@GpAGpAGnAEnAARCHCLCNGvBO|DGnBYxGK`CEtAGtAGrAGtAGtAEdA?H@D@JGnAGvAARAVCh@ExAGvAGxAGvAGxAGxAGvAGxAGxAGxAEvAGxAGxAGxAGzAGxAGxAGxAaA`WE~@ERCLCV?HIvAIjBIbCMdCk@hMAXGzAIzAGvAGpAEnAGjAGfAEhAEdAGhA?HYzGCbAE`B?HGnAANGtAItAEfAANGrAGtAGtA?D?F@JE|@GtAEtAGtAGtAGvAEtAGvAGtAEdAAPGtA?LEhAAHEhAEnAElAGjAElAGlAEjAElAGjACx@APEjAElACf@Ct@AbAAVAlA?R?`A?p@?j@A^AX@jAB`A@\\\\BZ@b@F~@H~@Fj@Fn@Fh@ABABA@A@?BABA@?B?BAB?B?@?B?B?B?B@@?B?B@B?@@B@@@B?@@@@@@@@@@@@?B@@?@?@@@?BA@LRdAJf@VtA@@Lj@Rt@|@jCl@~AJRn@zAVf@Xf@nBxCz@bANRXVh@d@PH|@n@f@\\\\^Vf@\\\\h@ZbBbAzCfBjAr@bAl@`B`AdAn@bEdCp@`@p@`@fGtD`Al@NHNHxCfBnBnA^ThTbN^ZB@b@h@n@x@`@d@?@l@l@?@A@?BA@?@?BA@?@?B?@?B?@?@?B?@?B@@?B@@@@?B@@@@@@@@?@@@@@@@@@@?@@@?@?@?@?@??A@?@A?A@??A@AT\\\\HJDBRDFHt@rAl@tA^z@d@jAb@rAb@vATx@J^h@vBX`BJt@@FVlBv@xFJ~@lArJh@nE\\\\lCNlANfADf@NzADr@JlADx@DbABpA?@BpCAjCCdAA~@GhAGhAKzAKjAIt@WjBIj@Or@g@hCu@~Ce@jBu@zCsFlUGRc@hBc@hB_@vAcA`EqApEQr@K\\\\Oh@EPe@~AMn@W`Ag@dBGVQp@Sp@?@Qj@]dAKXYv@Wn@{@rBkB|DuB`FgAhCcA`CMXk@lA_@|@a@|@_@z@_@z@_@|@_@z@_@x@]z@_@x@]v@O\\\\MZ]v@g@fAe@fAe@fAc@bAc@dAc@`Aa@~@a@|@_@z@GLABkAnCaAtBk@pAKT]v@KPSZe@z@\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2100737,\n" +
                "                \"lng\": 20.6735202\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"23.2 km\",\n" +
                "                \"value\": 23213\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"18 mins\",\n" +
                "                \"value\": 1098\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.1198816,\n" +
                "                \"lng\": 19.9318262\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>3rd</b> exit and stay on <b>DK92</b>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"i`v}HidizBC?AAA?A?C?A@A?C?A@A?A@A@A@A@A@?BA@ABAB?@?B?@AB?@?B?@?B?@?B@@?B?@@B@B@B@B@@@B@@B@@@B@B@B?B@BA@?DAd@t@LJHN`@x@Vb@x@|At@xAt@zA~@nBdBtDvAvCrDvHhBxDhBvDf@hAd@bA`@v@v@xAl@tA`@x@x@fBf@bAFJ\\\\t@Vd@@DJ`@nAdCb@dAd@dAd@|@@BP`@R`@f@bAf@dA`@v@FL^t@^t@JLBDDDFBFL^t@^v@Xj@DH`@v@^v@`@v@^v@`@v@^v@`@x@`@v@^x@`@v@`@x@`@v@^v@`@v@`@x@^v@`@x@`@v@`@x@^v@`@x@`@v@^x@`@v@HPVd@^x@`@v@^v@Td@JR^v@`@v@`@v@LVP`@`@x@Vd@^|@j@nA^|@BDZp@\\\\r@Xh@DF^r@\\\\p@FLjBhD`@v@f@fAh@fAf@fAh@fA\\\\t@HNdClFTf@`AdB|A`DNZLVLVbBdDDFNJ@@Zn@\\\\t@^r@^t@\\\\t@^r@^t@\\\\r@Tb@HPXj@BH@?\\\\r@HNTd@^t@\\\\r@^t@^t@DHXj@^t@\\\\r@^t@^t@^t@^t@^t@^v@^t@^t@^t@^r@^v@^t@^t@^r@^t@^t@^t@^t@^t@^t@\\\\t@^t@Xj@DRXt@^t@T`@dApBlAlB`@j@?@^r@\\\\r@^t@^t@^t@`@v@^t@NZNXDHVh@\\\\p@Zr@\\\\r@HLRd@DDZp@b@x@`@x@`@x@`@x@`@x@`@v@`@x@^v@`@v@^v@DHNh@\\\\t@Xh@Td@d@|@Tb@JN~@z@d@\\\\f@\\\\d@VHDHDN@LH~AhApA|@b@\\\\B@h@^f@`@h@^h@`@h@^h@^h@\\\\h@\\\\h@^PLVNf@\\\\h@^h@\\\\h@^h@\\\\j@^h@^h@\\\\h@^j@^h@^\\\\TLHj@^`@XHFl@^j@^l@`@j@`@j@^@?l@`@j@`@@?j@^j@`@l@^j@`@j@^j@`@j@^j@^l@`@j@^j@^j@`@TNTNl@`@j@^l@`@j@^B@LTVRp@d@nAx@l@b@j@`@z@f@FBTBn@d@j@^j@`@l@^j@^j@^l@`@h@^j@`@B@f@\\\\j@^j@^j@`@j@^j@^j@^j@^h@^j@^j@^j@^j@^j@^j@^j@`@j@^`@XB@VZbAp@HD`@\\\\\\\\XJFRLrBvAJDLDR@f@^h@^LH\\\\Th@\\\\j@^h@\\\\fBlATNPJJFh@^h@^h@^`@VFDh@\\\\@@h@^j@^h@\\\\j@^h@^j@^j@^j@^h@^ZRNJh@^j@^h@\\\\h@^h@^h@^j@\\\\h@^h@^h@^h@`@f@d@HH\\\\\\\\d@h@f@j@d@l@b@n@PVPXb@p@Xh@FJ^t@\\\\v@^z@Pb@JVZ|@Z`ABLRp@VbAV`ATdAPbARdAPdANdAPdAPbAPdANdAPbAPdANdAPdAPfAPdAPdAPdAPfAPfAPdAPfAPfANfAPdA@BNbALp@BRPdAPdANdAPdAPdAPdAPfAPfAPfAPhAPfARhAPfAPfAPdANz@@JPdANdAPfANz@@HPfAPfAPfARfAPfAPhAPhAPfAPhARjARhAPjAF\\\\@D@ZRnA`@jCBTJj@r@rEHXHXLr@PfAPhAPhARhAPhARjAPhARjALx@DPPjARjAPhAPhAPhAPfAPfAPfAPdAPdAPdAPfAPdAPfAPdATzALt@DXF\\\\Hj@PjAPhARjAPhARjAPjARhAPjARjAPjARhAPhAPhAN~@BHHl@FZPfAPhARhAPhAPhARjAPjARjAPjARjARlARjAPlARjABLN|@PlARjAPjARlARjARlARlAPlARjARlAF`@Hh@RjAHj@F^RhAPjAPhAPfAPhAPfAPdAPdANdAPdA@DN~@NdAPdAPdAPdAPdAPdANdAPfAPdAPdAF`@?DB\\\\`@fCb@hDJj@PdAPfABHN|@BPDRHRBJPhAPfA@FFZHf@PhAPhAPjARhAPjAPjAHh@F^Jn@DVPhAPfAPhAPfAPdAPfANfAPdAPdAPdANdAPdAPdANfALt@BNHd@F^PfAPfAPhAPhAPhARjAPlARlABTNv@PnARlA@BJt@DRRlAPjAPjARhAPfANfAPbANbAPbANdAPbA?BN`APdAPdANbAPdAPdANdAPdAPdANdAPbA?@NbAPbATxATvAPlA@FRrATrARrARpATpARnARpADVF`@DVRpARrATrARtATtATvATvARlA@JTxAPbANdAHd@F\\\\BPJp@F\\\\Fd@PbANdAPbA@JLx@PdANdAH`@Fb@PfAPfAPfAPfAPfAPhAPhAPhAPhAPjARhANfAPfAPfAPbANdAPbANbANbATxATvATtARrAJl@Hf@RtATrARpAPfAPpAb@rC@FPfAPfAPhAPfARlARtALt@BPFb@Hf@RlAPjAPlARjAPlARjAPlARnARlAPnARnARnARpARpARnARpALv@DXRpARpADX@Dt@zEPjARrALt@F\\\\RrARrATtARrARrATtARtAF\\\\Lx@Nr@V|Ah@pDRhAL~@?@XrBJj@NbAHb@F^`@tCrDbVnA~HrAlIl@~Df@~CXdB`@bCTnBNfAb@vCHh@p@nERhANhAPdA@`@JfABh@BV@j@@J@j@?\\\\?VA^?BG`BMlGKdGAl@A\\\\G~E?X@ZI|FAxAGfDAt@AZKbFAn@?z@EzAG`BCdCk@t_@[bS?DEXChAG|BItG?D?n@IxDE|AM`FEdH@h@?BSpJYlT?dAAf@Af@OrICx@Ex@Ej@{@bLm@hHIhAOpBWdDa@jD_@jEQlB\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.2190945,\n" +
                "                \"lng\": 20.2043716\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"4.0 km\",\n" +
                "                \"value\": 3962\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"5 mins\",\n" +
                "                \"value\": 275\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 52.08774829999999,\n" +
                "                \"lng\": 19.9201118\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>left</b> onto <b>Zamkowa</b>/<wbr/><b>DK14</b><div style=\\\"font-size:0.9em\\\">Continue to follow DK14</div>\",\n" +
                "              \"maneuver\": \"turn-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"gtb}H}|sxBPFx@^@@ZNh@Z|Az@BBh@X^PLDv@Vx@Nx@H`ADxABN?|CFvIR~GN`BFvDBxB?lBD`AD^D`@H^Lj@Rp@^j@`@`@X\\\\^l@r@\\\\h@Zf@Xh@Vh@\\\\v@\\\\bANn@p@lC|BvIXlAJf@DZPv@n@pC|@vD`@nAb@lA^bA^|@LTNJ^n@^l@V^\\\\b@j@p@l@h@n@h@p@f@b@X@?d@Th@Th@Nz@P\\\\D^D`@D^@f@?f@AlAGjAGz@GtDYtDWjE[vDYxFa@`Kq@~BQp@Ej@AlBSJAp@E~E_@lD[vAKD?pBG\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.1198816,\n" +
                "                \"lng\": 19.9318262\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"29.8 km\",\n" +
                "                \"value\": 29762\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"26 mins\",\n" +
                "                \"value\": 1542\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.9078268,\n" +
                "                \"lng\": 19.6148152\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>2nd</b> exit onto <b>Prymasowska</b>/<wbr/><b>DK14</b><div style=\\\"font-size:0.9em\\\">Continue to follow DK14</div>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"mk||HusqxB@D?D@B?B@B@B@@@B@@@BB@@@@@B?@@@?B?@?B?@A@?BA@A@A@CBA@C@A?C@C@C?C@C?E@C?C?Iv@IP?ZA\\\\Av@BH?L@VBRDPBVFHDn@TtAz@fAv@hD`CjD~BfSnNdQvLHH`@\\\\f@\\\\~AhA@?lM`J~O|K|NfKbQ|LhChBpE~CdCdBnClBrE`DjD`CrCnB`FnDnCpBpFvDjBrAjCjBvDpCpIbGpGpEvBxATPfDbClAbA^f@JRn@nAx@nBlAzC~AzD~B|FPd@`@`A`BvDxBrFVj@tCdHhDpIVj@Tl@|DrJrAhDHL?BfCfG~@~BlHtQZz@dChGRd@z@vB`BfE\\\\v@N`@|EjLl@|AHRnExKvApDdAjCb@bAZx@|ArDfAjCh@rA`DxHTl@@BTf@Tl@~@|BzBpFdAhCfAlCjApCZv@`AbCbC~Fz@vBN\\\\N^f@lAdAfCfBlEDLDH^`ApBzE|@zBxAnDdApCvNz]|GnP|AxDfClG|FpNdBdEFPlOf_@fAtC|S~g@r@dBhHhQ~DzJbAbCb@pANf@Rv@FTXtALh@d@tBBLh@vCNx@z@hEbBrIJf@XtAv@~Dj@nCt@|D@XnAnGPv@Ln@J^f@`BFNTh@JPJJrCdGfAvBtCxFnCpF^r@tApC`BbDLV~AbDrGpM`AnBbHjNlAdCnDjH^r@`AlBXj@b@|@rEfJ|CfGVh@jA|BVh@jA|Bp@rAbBfDXh@p@rADHP^xC~F|EtJVh@Xf@Vh@zAxCpEzIVh@`AjBbGtLbBhDpFtKzFbL`AlBzGpNzGpN@BbDvGZl@vJzRlK`TXh@Vh@p@rAzIhQrGnMl@nAxElJFNtCzFXj@hFdKf@bAhAzBnCrFbCxEtApCr@tA`@j@V\\\\TTVPXPHD|FlDZRJHNTjA|@@BBDPRNRV^Z`@nBvBXRZf@NXJTP^FPDLLb@Hl@Jj@Fd@DZHlAD~@Bh@Dn@Bd@HvALdBFh@f@|MBz@Bx@ZjIR~EL|CNtDXvGVfHDx@HpBBn@X`HBj@Bx@Bf@?z@D`ARfG@JBz@HhARxCFx@H`AHl@HnA@NFx@LpB?FBZBTDbAV`DFv@?FDj@@VLx@Px@Rl@L`@d@jAf@bA`@v@lBnEbDlHVj@zEnKzB~E~D|I|DzI|AjDh@lAx@fBtA|CfGrNx@hBnFpLVh@xA~CjGdNl@vAtJjTrAxCv@dBl@tAv@hBp@|AL\\\\@Bx@bCn@bBRl@`@fAp@nBjAdD~BrGtA|Dv@vBTn@d@rAZz@~AnEtB~FPd@BFnArDHVbBxETn@tAxDjBhFh@|ATn@Pd@f@tAbGvPvAxD~BvGTn@zDvKFNhBjFXt@Tn@Rl@\\\\`Ad@nAVp@h@nAHLJLV`@TXTThA`Af@b@^Z~JhI\\\\X`FdEpBdBf@`@f@d@BBLJNNV\\\\DF\\\\l@RZ|AvCPZpNdY?@Vj@`@t@dBlDtDlHzAxCv@|AzBrEp@tANXxEjJ|D~H@@p@rAXh@Vh@jA|BrGhMl@hAdBfDVf@LTJR@BXf@d@z@dApB`AjB~@lBdFnK|BpElEzIrGfMFXV|@\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 52.08774829999999,\n" +
                "                \"lng\": 19.9201118\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"3.6 km\",\n" +
                "                \"value\": 3586\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"5 mins\",\n" +
                "                \"value\": 279\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.8828961,\n" +
                "                \"lng\": 19.5829331\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>2nd</b> exit onto <b>Warszawska</b>/<wbr/><b>DK14</b><div style=\\\"font-size:0.9em\\\">Continue to follow DK14</div>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"}fy{Hs_vvB?BAB?D?B?B?B?D?B?B@D?B@B?BBB?B@BB@@@@B@@@?B@B@@?@@B?@AB?@?@A@ABAf@v@LTlChFh@bAXh@t@zA|@dBrAjCxCbGr@zAfAtB`@x@r@xAv@`BnAfCnBrDbA|AjBvCT\\\\nDjFTh@b@p@tB|Cl@v@l@n@d@`@\\\\RJJr@v@nA|AfCzCnAzA`AfAdLpMfFvF|AdBzA~Ah@j@HJLV|BrCd@j@f@j@tDdEt@f@xCxD`ClCRTf@j@vCdDpBxBdBnBzBfCrBzBdFbF`A|@zAnALRJLFPDJBNDV@N?TAVCPIb@\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.9078268,\n" +
                "                \"lng\": 19.6148152\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.8 km\",\n" +
                "                \"value\": 773\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 65\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.8771,\n" +
                "                \"lng\": 19.578054\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>3rd</b> exit onto <b>DK1</b>/<wbr/><b>DK14</b>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"ckt{HixovBA?A@A?A@?@A?A@?@A@A@?@A@?@?BA@?@?@?BA@?@?B?@?@?B?@@@?B?@@@?@?B@B@@?@@?@@?@@@@?@@@@@?@?@@B?@?@A@?@A@?@A?A@A@??A@A@CvBDx@TZJVFNBFBHDVLVLTPTPXV^\\\\bA`AjDbD^\\\\pClCp@n@f@d@@FBBFHPNZ\\\\XXdAjA^b@JLHP\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.8828961,\n" +
                "                \"lng\": 19.5829331\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"11.2 km\",\n" +
                "                \"value\": 11160\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"10 mins\",\n" +
                "                \"value\": 621\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.7994992,\n" +
                "                \"lng\": 19.487869\n" +
                "              },\n" +
                "              \"html_instructions\": \"At the roundabout, take the <b>2nd</b> exit and stay on <b>DK1</b>/<wbr/><b>DK14</b>\",\n" +
                "              \"maneuver\": \"roundabout-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"{fs{HyynvB?D?F?D@F@D?DBD@D?@@BBBBDBBFBB@B?B@BAD?BABABCBC@AD@D@B@FD`Av@~@t@h@b@TPJBxApA^Z`DfC|DzCRN~BfBtDrChBvA`BnAVRfGvE\\\\XpDpCJFdE~CbDdCn@d@x@h@nAx@FBXPb@TfAh@z@`@j@Rn@TbBl@^NXHv@XnA`@zDlAl@TZJ\\\\Lj@RbBj@nDhAbCx@fA\\\\bDdAVHJJtBr@~E~AV@nBn@fBl@hBj@rFfB`@N`@LpGtBhA^|@XtDlA`@L\\\\J|HhC`@L|Bt@xDnA|E~Av@TrAd@bAZbA`@\\\\NTLNHRLVNVRPNr@l@v@~@h@r@Zf@RZR^b@|@f@nAx@xBX|@l@dBJXpCvIv@|BfCnHdA~C`@lA@@L`@?@LZ`CjH`@jA|@lCvAfE~CrJjBpFrAzDX|@L\\\\@FVt@xDjLzBvGxAjE|DrLtAdEn@hB~@rChAfDNf@Rh@~@pC^jAPf@vBnGvAjElDhKhAdDdA|Cf@bBrDvKDJtAbEhAfD~@pC`@jA\\\\fAN`@HVVr@|AvE|@lCvBtGjBrFzDhL?@`B|EDL|ArEDP~B~GL\\\\Tj@NZ\\\\j@Zh@X\\\\ZVXVLHRLTJRH^NVFbBb@dAXzA`@`AVf@Nb@JbEfA~@VpD`AHBhBb@bBd@hAXhCp@jCr@zCx@RDlCr@b@LVHb@PPJTLZTLJJLLLLNDFJNLPd@|@n@nA^x@~AbD`@v@P`@`@t@\\\\r@JPT`@RZXf@Zf@PTd@r@j@v@r@bAb@l@HNtC`En@`APVNXZh@LVLVLVL\\\\|@lBlAlCf@jABD^v@HRHPBRn@xAN\\\\HTPl@Tx@Pf@Vr@f@lAh@fAb@p@PZ\\\\^z@t@JHl@^\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.8771,\n" +
                "                \"lng\": 19.578054\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"2.4 km\",\n" +
                "                \"value\": 2353\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"5 mins\",\n" +
                "                \"value\": 271\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.7794503,\n" +
                "                \"lng\": 19.4812019\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue straight onto <b>Strykowska</b>/<wbr/><b>DK14</b>/<wbr/><b>DK72</b><div style=\\\"font-size:0.9em\\\">Continue to follow DK14</div>\",\n" +
                "              \"maneuver\": \"straight\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"{ad{Hef}uBd@PFBF@@@l@RDBf@Ff@@f@Al@ErBSnI}@`CWfKaAp@EH?lBNRDj@Xj@`@b@b@LNj@d@~@~@HDl@f@\\\\VtFvEtAhABBrDxC|AnA\\\\Zt@n@x@l@l@XB@ZN\\\\Ph@Rl@Pb@H~@LdADjGPlEJvBF|@@v@@l@J`A\\\\tAr@rC`D\\\\b@TVJN\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.7994992,\n" +
                "                \"lng\": 19.487869\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"2.0 km\",\n" +
                "                \"value\": 2005\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"4 mins\",\n" +
                "                \"value\": 229\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.761625,\n" +
                "                \"lng\": 19.4848506\n" +
                "              },\n" +
                "              \"html_instructions\": \"Slight <b>left</b> onto <b>Dr. Stefana Kopcińskiego</b>/<wbr/><b>rondo Solidarności</b>/<wbr/><b>DK14</b><div style=\\\"font-size:0.9em\\\">Continue to follow Dr. Stefana Kopcińskiego/<wbr/>DK14</div>\",\n" +
                "              \"maneuver\": \"turn-slight-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"qd`{Ho|{uBXL@?FBLDZ@D?@@DAVCHATMVUBAj@_@d@O\\\\CVGTCvBWdC[hAOb@ElC_@l@GLAVEhBWlAUzASB?n@GXCJAD?B?FAb@A\\\\Et@Kt@K^EzEk@jAOhBULC`Fi@j@G`@GXC`AMBA@?FATCXEdAMnDm@`@E`@GZARAL?rBEb@Cn@IVCjFs@`@Gj@G@?l@EnAMnASREPEPG\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.7794503,\n" +
                "                \"lng\": 19.4812019\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"1.2 km\",\n" +
                "                \"value\": 1182\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"2 mins\",\n" +
                "                \"value\": 130\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.7603151,\n" +
                "                \"lng\": 19.4678275\n" +
                "              },\n" +
                "              \"html_instructions\": \"Turn <b>right</b> onto <b>al. Marszałka Józefa Piłsudskiego</b>\",\n" +
                "              \"maneuver\": \"turn-right\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"cu|zHis|uBFdAFz@B`@HpDJjBBdAB|@Dr@JxADn@BTFxANfEDvABnADjABdBDdEBtAFhEDfBHnBLrCBx@?DNzDBx@@VJnCFzADn@DlAP|FLlD\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.761625,\n" +
                "                \"lng\": 19.4848506\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.6 km\",\n" +
                "                \"value\": 617\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 52\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.75947,\n" +
                "                \"lng\": 19.4589865\n" +
                "              },\n" +
                "              \"html_instructions\": \"Slight <b>left</b> to stay on <b>al. Marszałka Józefa Piłsudskiego</b>\",\n" +
                "              \"maneuver\": \"turn-slight-left\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"_m|zH}hyuB@@R~@XdFl@dOHrB`AdY\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.7603151,\n" +
                "                \"lng\": 19.4678275\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"distance\": {\n" +
                "                \"text\": \"0.2 km\",\n" +
                "                \"value\": 208\n" +
                "              },\n" +
                "              \"duration\": {\n" +
                "                \"text\": \"1 min\",\n" +
                "                \"value\": 17\n" +
                "              },\n" +
                "              \"end_location\": {\n" +
                "                \"lat\": 51.7592212,\n" +
                "                \"lng\": 19.4559892\n" +
                "              },\n" +
                "              \"html_instructions\": \"Continue onto <b>aleja Adama Mickiewicza</b>\",\n" +
                "              \"polyline\": {\n" +
                "                \"points\": \"ug|zHuqwuBf@dNHpB\"\n" +
                "              },\n" +
                "              \"start_location\": {\n" +
                "                \"lat\": 51.75947,\n" +
                "                \"lng\": 19.4589865\n" +
                "              },\n" +
                "              \"travel_mode\": \"DRIVING\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"traffic_speed_entry\": [\n" +
                "            \n" +
                "          ],\n" +
                "          \"via_waypoint\": [\n" +
                "            \n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      \"overview_polyline\": {\n" +
                "        \"points\": \"__x}Hsqg_CpA]DBf@Si@oEY_CcA^mGjCkAb@h@|DvAfLT`GLhB`ArCjDrWtD~[bB`KrBzOP~Ap@nF_Bt@iD~Aq@h@wOlIwDfBy@f@f@hDdAbJ`@tGvBxQVtFxA`IbA~Iz@pQjAxUv@|Zv@vZhApp@jCbk@bInhA`Ljm@tNd_AlObdA`Gtb@fAhSvAlZMh@F|\\\\BffCdEbfBdDpnArEdi@fUnpCEpWk@bt@m@`v@}BxjC_BtcCeA`rAaApaBmA|}BbM`wBrMdxBnc@fuCvRvpAxBzPo@`j@gAvs@oBpsAsDfkCuDxaC[vWcEt_AkKfeCmOtrDsFdpAcEn`AsBxi@cHx_BuDh}@{JzbCaJ|uBwKhlCyLltCyKvkCeFlsAoI`qBmD||@aAd]F~If@pHGt@Z`@bArEzFlOtEhG`D|BbR`LvUpNr]pTvB`CjA`BATNb@TDj@d@~BxDlCrHbExUvEj`@b@pRoAbRoMrj@kHzXgDdMuGfPiShe@sPz_@iCxDQh@Tn@`Av@vH|N|]zt@zLhWbKbSd]xr@p|@`gBlM|WrMnVhMhW~DrIbFlDhSnNrQxLlc@fZxS|MxT`OvFfErGzDxOhKzQ|LnIdGbFlG`ElIfDnMzG~a@pN~|@|OdbAda@bgCt\\\\rvBh~@f_Gzm@z}DzDbXZlHeAdr@mCz_BeAxv@W|MuBrXsAlOq@xHjAf@dDhBzAt@lFv@~Q^d\\\\v@hDbBjCvCjBrD|ExQpFpTrEdIzDlDtBdAtHr@jdAiH|Jo@BRPVh@OFa@dBUlBBtATvEnCvu@xh@f{Bd~Ajh@f_@tMvZpl@txAne@|jAnxA~nD~z@`uBjCtHtAjGzHz`@fG`Zvn@toAxd@v~@xv@n|AxrAvmCrWfh@fBpB|HxEfDnDvFvHfAzIxA`[bEbgAbCfb@zAfMbSbd@xdAv~BhIlSvMd_@|Xpw@~^pcApa@h^`i@feAdb@hy@t_@jv@T|A\\\\r@RC~JdRfQ~]tSl\\\\dm@fq@xEbGfOtPzStUnL`MIjCQ\\\\Dj@XJJGzBA|Bl@fD~B`OrNjE`FHr@z@LtYzT|ZzUhRzMtLvErThHvu@nV`o@`TtA`ApDhE`G`O|_@hjAhdAb}C|]~dAnBjCpAz@zLhDnl@xO~BnBbHfNhQhXrK`VpE`LrEpD|@ZdD@l]kD|DNlHbGrUrR`HzCzZhAvCpApDdE|@t@`BDnEeBlQyBhXcDd\\\\eE~Hc@|OqBb@MN`C\\\\dK|@jSjBls@j@hP|AlX|Bpo@\"\n" +
                "      },\n" +
                "      \"summary\": \"DK92 and DK14\",\n" +
                "      \"warnings\": [\n" +
                "        \n" +
                "      ],\n" +
                "      \"waypoint_order\": [\n" +
                "        \n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"OK\"\n" +
                "}";
    }

    private String getFinalResult() {
        return "{\n" +
                "  \"waypoints\": [\n" +
                "    {\n" +
                "      \"id\": \"4cb048da1463a1435f779ba9\",\n" +
                "      \"name\": \"Dom Urodzenia Fryderyka Chopina w Żelazowej Woli\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Żelazowa Wola 15\",\n" +
                "          \"96-503 Sochaczew\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 52.258019719126395,\n" +
                "        \"lng\": 20.310273957020996\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"4cb048da1463a1435f779ba9\",\n" +
                "        \"avg_rating\": 5,\n" +
                "        \"number_of_ratings\": 330\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4c487a5796abd13a6d767401\",\n" +
                "      \"name\": \"Muzeum kolejki waskotorowej\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"ul. Towarowa\",\n" +
                "          \"96-500 Sochaczew\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 52.220529477379316,\n" +
                "        \"lng\": 20.23335939093059\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"4c487a5796abd13a6d767401\",\n" +
                "        \"avg_rating\": 3,\n" +
                "        \"number_of_ratings\": 718\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4c5d59546ebe2d7f407fd22e\",\n" +
                "      \"name\": \"Pałac w Nieborowie\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Nieborów\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 52.067975859823065,\n" +
                "        \"lng\": 20.069862700908168\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"4c5d59546ebe2d7f407fd22e\",\n" +
                "        \"avg_rating\": 4,\n" +
                "        \"number_of_ratings\": 643\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4c66c072e1da1b8da0c49bc3\",\n" +
                "      \"name\": \"Oberża pod Złotym Prosiakiem\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Nieborow 175 (droga na Zygmuntów)\",\n" +
                "          \"99-416 Nieborow\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 52.07301102273241,\n" +
                "        \"lng\": 20.051080644690177\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"4c66c072e1da1b8da0c49bc3\",\n" +
                "        \"avg_rating\": 3,\n" +
                "        \"number_of_ratings\": 267\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5948067b17556232c3b7b42b\",\n" +
                "      \"name\": \"MANGAL\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Piotrkowska 71 (Stanisława Moniuszki)\",\n" +
                "          \"90-422 Łódź\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 51.76803156480234,\n" +
                "        \"lng\": 19.45671094579505\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"5948067b17556232c3b7b42b\",\n" +
                "        \"avg_rating\": 5,\n" +
                "        \"number_of_ratings\": 948\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"539224e2498e2185a92bf04e\",\n" +
                "      \"name\": \"Z Innej Beczki\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Moniuszki 6\",\n" +
                "          \"Łódź\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 51.76836066857008,\n" +
                "        \"lng\": 19.458965621288428\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"539224e2498e2185a92bf04e\",\n" +
                "        \"avg_rating\": 2,\n" +
                "        \"number_of_ratings\": 598\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5632229e498e62f33721f5eb\",\n" +
                "      \"name\": \"Gastromachina Stacja\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Piotrkowska 89\",\n" +
                "          \"Łódź\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 51.7662137351591,\n" +
                "        \"lng\": 19.456500597102796\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"5632229e498e62f33721f5eb\",\n" +
                "        \"avg_rating\": 5,\n" +
                "        \"number_of_ratings\": 729\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5085a966183f555704a5bdf7\",\n" +
                "      \"name\": \"Pomodoro. Pizzeria i Stuzziccheria\",\n" +
                "      \"location\": {\n" +
                "        \"formattedAddress\": [\n" +
                "          \"Rewolucji 1905 roku 4 (Piotrkowska)\",\n" +
                "          \"90-273 Łódź\",\n" +
                "          \"Polska\"\n" +
                "        ],\n" +
                "        \"lat\": 51.77486015806364,\n" +
                "        \"lng\": 19.45608235463843\n" +
                "      },\n" +
                "      \"rating\": {\n" +
                "        \"id\": \"5085a966183f555704a5bdf7\",\n" +
                "        \"avg_rating\": 4,\n" +
                "        \"number_of_ratings\": 975\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"original_distance\": 137666,\n" +
                "  \"final_distance\": 152091,\n" +
                "  \"original_duration\": 8934,\n" +
                "  \"final_duration\": 15456\n" +
                "}";
    }
}
