package net.kamilereon.lylac.market;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import net.kamilereon.lylac.LylacUtils;
import net.kamilereon.lylac.database.MongoConfig;
import net.kamilereon.lylac.database.MongoConfig.LylacMongoCollections;
import net.kamilereon.lylac.entity.Player;
import net.kamilereon.lylac.event.player.LylacPlayerMarketOpenEvent;
import net.kamilereon.lylac.item.ItemUtil;
import net.kamilereon.lylac.permission.LylacPlayerPermission;
import net.kamilereon.lylac.permission.LylacPlayerPermission.LylacPlayerPermissionType;

public class LylacMarket {

    private static final int[] ITEM_LOCATION = {0, 1, 2, 3, 4, 5, 6,
                                                9, 10, 11, 12, 13, 14, 15,
                                                18, 19, 20, 21, 22, 23, 24,
                                                27, 28, 29, 30, 31, 32, 33,
                                                45, 46, 47, 48, 49, 50, 51};

    private final Inventory market = Bukkit.createInventory(null, 54, "market");
    private final Player player;
    private boolean LYLAC_MARKET_SHOW_ITEM_LOADING = false;

    public LylacMarket(Player player) {
        this.player = player;
    }

    private void loadItems(int page) {
        LYLAC_MARKET_SHOW_ITEM_LOADING = true;
        try {
            MongoDatabase database = MongoConfig.mongoDatabase();

            MongoCollection<Document> marketDocument = MongoConfig.getMongoCollection(database, LylacMongoCollections.market);
            MongoCollection<Document> rootItemDocument = MongoConfig.getMongoCollection(database, LylacMongoCollections.rootItem);
            // 날짜 기준 내림차순으로 54개 찾기
            FindIterable<Document> marketResult = marketDocument
                .find()
                .sort(Sorts.descending("registeredDate"))
                .limit(54)
                .skip((page - 1) * 54);

            // iterator 형식으로 변환
            MongoCursor<Document> _marketResult = marketResult.iterator();

            for(int i=0; i<ITEM_LOCATION.length; i++) {
                if(_marketResult.hasNext()) {
                    Document marketObject = _marketResult.next();

                    Document itemObject = Document.parse(marketObject.get("item").toString());
                    String itemUUID = itemObject.getString("uuid");
                    //Date date = itemObject.getDate("registeredDate");

                    Document rootItemResult = rootItemDocument.find(Filters.eq("uuid", itemUUID)).first();
                    ItemStack itemStack = ItemUtil.getItemStackFromDocument(itemObject, rootItemResult);
                    market.setItem(ITEM_LOCATION[i], itemStack);
                }
                else {
                    market.setItem(i, new ItemStack(Material.AIR));
                }
                
            }
            LYLAC_MARKET_SHOW_ITEM_LOADING = false;
        }
        catch(Exception e) {
            LYLAC_MARKET_SHOW_ITEM_LOADING = false;
        }
    }

    public static LylacMarket openMarket(Player player) {

        // 플레이어가 마켓을 열 수 있는 권한이 있는지 체크
        if(!player.getPermission().checkPermission(player,
             LylacPlayerPermissionType.LYLAC_MARKET_OPEN_GUI,
              "마켓에 진입 할 수 있는 권한이 없습니다.")) return null;

        // 플레이어가 마켓을 열 때 이벤트 방출
        LylacPlayerMarketOpenEvent event = new LylacPlayerMarketOpenEvent(player);
        LylacUtils.Event.callEvent(event);
        if(event.isCancelled()) return null;

        LylacMarket lylacMarket = new LylacMarket(player);
        lylacMarket.loadItems(0);
        player.getBukkitEntity().openInventory(lylacMarket.market);
        return lylacMarket;
    }

    public void changePage(int page) {
        this.loadItems(page);
    }

    /**
     * 
     * @param uuid 구매하고자 하는 아이템의 uuid
     */
    public void buyItem(String uuid) {

    }

    public void getMoney() {

    }
}
