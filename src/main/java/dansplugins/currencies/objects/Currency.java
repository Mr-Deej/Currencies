package dansplugins.currencies.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dansplugins.factionsystem.externalapi.MF_Faction;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Currency implements ICurrency, Savable {
    private String name;
    private String factionName;
    private String material;
    private int currencyID;
    private int amount;
    private String description;
    boolean retired;

    public Currency(String name, MF_Faction faction, Material material, int currencyID) {
        this.name = name;
        this.factionName = faction.getName();
        this.material = material.name();
        this.currencyID = currencyID;
        amount = 0;
        description = "defaultDescription";
        retired = false;
    }

    public Currency(Map<String, String> data) {
        this.load(data);
    }

    @Override()
    public String getName() {
        return name;
    }

    @Override()
    public void setName(String name) {
        this.name = name;
    }

    @Override()
    public String getFactionName() {
        return factionName;
    }

    @Override()
    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    @Override()
    public String getMaterial() {
        return material;
    }

    @Override()
    public void setMaterial(String material) {
        this.material = material;
    }

    @Override()
    public int getCurrencyID() {
        return currencyID;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public void decreaseAmount(int amount) {
        this.amount -= amount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public boolean isRetired() {
        return retired;
    }

    @Override
    public void setRetired(Boolean b) {
        retired = b;
    }

    @Override()
    public Map<String, String> save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;

        Map<String, String> saveMap = new HashMap<>();
        saveMap.put("name", gson.toJson(name));
        saveMap.put("factionName", gson.toJson(factionName));
        saveMap.put("material", gson.toJson(material));
        saveMap.put("currencyID", gson.toJson(currencyID));
        saveMap.put("amount", gson.toJson(amount));
        saveMap.put("description", gson.toJson(description));
        saveMap.put("retired", gson.toJson(retired));

        return saveMap;
    }

    @Override()
    public void load(Map<String, String> data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        name = gson.fromJson(data.get("name"), String.class);
        factionName = gson.fromJson(data.get("factionName"), String.class);
        material = gson.fromJson(data.get("material"), String.class);
        currencyID = Integer.parseInt(gson.fromJson(data.getOrDefault("currencyID", "-1"), String.class));
        amount = Integer.parseInt(gson.fromJson(data.getOrDefault("amount", "-1"), String.class));
        description = gson.fromJson(data.get("description"), String.class);
        retired = Boolean.parseBoolean(gson.fromJson(data.get("retired"), String.class));
    }
}