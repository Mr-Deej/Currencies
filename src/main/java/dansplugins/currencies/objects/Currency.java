package dansplugins.currencies.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dansplugins.factionsystem.externalapi.MF_Faction;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Currency {
    private String name;
    private String factionName;
    private String material;
    private int currencyID;

    public Currency(String name, MF_Faction faction, Material material, int currencyID) {
        this.name = name;
        this.factionName = faction.getName();
        this.material = material.name();
        this.currencyID = currencyID;
    }

    public Currency(Map<String, String> data) {
        this.load(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public Map<String, String> save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;

        Map<String, String> saveMap = new HashMap<>();
        saveMap.put("name", gson.toJson(name));
        saveMap.put("factionName", gson.toJson(factionName));
        saveMap.put("material", gson.toJson(material));
        saveMap.put("currencyID", gson.toJson(currencyID));

        return saveMap;
    }

    private void load(Map<String, String> data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        name = gson.fromJson(data.get("name"), String.class);
        factionName = gson.fromJson(data.get("factionName"), String.class);
        material = gson.fromJson(data.get("material"), String.class);
        currencyID = Integer.parseInt(gson.fromJson(data.getOrDefault("currencyID", "-1"), String.class));
    }
}