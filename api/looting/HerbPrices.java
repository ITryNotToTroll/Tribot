package scripts.api.looting;

import scripts.api.zybez.wassupwassup.zybezUtils.ZybezItem;

public class HerbPrices {

    public static double getPriceHerb(int id){
        switch (id){
            case 199:
            case 200:
                ZybezItem guams = new ZybezItem("Clean guam");
                return guams.getAverage();
            case 201:
            case 202:
                ZybezItem marrentills = new ZybezItem("Clean marrentill");
                return marrentills.getAverage();
            case 203:
            case 204:
                ZybezItem tarromins = new ZybezItem("Clean tarromin");
                return tarromins.getAverage();
            case 205:
            case 206:
                ZybezItem harralanders = new ZybezItem("Clean harralander");
                return harralanders.getAverage();
            case 207:
            case 208:
                ZybezItem ranarrs = new ZybezItem("Clean ranarr");
                return ranarrs.getAverage();
            case 209:
            case 210:
                ZybezItem irits = new ZybezItem("Clean irit");
                return irits.getAverage();
            case 211:
            case 212:
                ZybezItem avantoes = new ZybezItem("Clean avantoe");
                return avantoes.getAverage();
            case 213:
            case 214:
                ZybezItem kwuarms = new ZybezItem("Clean kwuarm");
                return kwuarms.getAverage();
            case 215:
            case 216:
                ZybezItem candatines = new ZybezItem("Clean candatine");
                return candatines.getAverage();
            case 217:
            case 218:
                ZybezItem dwarfWeeds = new ZybezItem("Clean dwarf weed");
                return dwarfWeeds.getAverage();
            case 219:
            case 220:
                ZybezItem torstols = new ZybezItem("Clean torstol");
                return torstols.getAverage();
            case 3051:
            case 3052:
                ZybezItem snapdragons = new ZybezItem("Clean snapdragon");
                return snapdragons.getAverage();
        }
        return 0;
    }

}
