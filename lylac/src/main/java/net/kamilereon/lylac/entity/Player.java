package net.kamilereon.lylac.entity;

import org.bukkit.Bukkit;
import org.bukkit.inventory.PlayerInventory;

import net.kamilereon.lylac.Lylac;
import net.kamilereon.lylac.Utils;
import net.kamilereon.lylac.item.artifact.ArtifactInventory;

/**
 * @author kamilereon
 * @version 1.0.0
 */
public class Player extends Entity<org.bukkit.entity.Player> {

    /*
     * 아티팩트 인벤토리
     */
    private final ArtifactInventory artifactInventory = new ArtifactInventory(this);

    protected int maxMana;
    protected int mana;
    
    /*
     * 마나 재생률
     * 최소 0
     */
    protected int manaRegenRate = RATE_DEFAULT;

    /*
     * 마나 소모률
     * 최소 0
     * 
     * 예1) 마나 소모률이 120이면 원래 마나 소비량의 1.2배 증가됨
     */
    protected int manaConsumptionRate = RATE_DEFAULT;

    /*
     * 스펠 캐스팅 속도
     * 최소 0
     * 
     * 예1) 스펠 캐스팅 속도가 80이면 원래 캐스팅 속도에서 0.8배 느려짐
     */
    protected int spellCastingSpeed = RATE_DEFAULT;

    /*
     * 스펠 캐스팅 간 이동속도
     * 최소 0
     * 
     * 예1) 스펠 캐스팅 간 이동속도가 120이면 원래 스펠 캐스팅 간 이동속도에서 1.2배 빨라짐
     */
    protected int speedWhileSpellCasting = RATE_DEFAULT;

    /*
     * 속성 증폭률 즉, 데미지 증가률
     * spellAmlificationRate는 모든 속성에 대해서 적용
     * 
     * 최소 0
     * 예1) 공기증폭률이 120이면 데미지 계수는 1.2로 계산
     * 예2) 공기증폭률이 80이면 데미지 계수는 0.8로 계산
     */
    protected int spellAmplificationRate = RATE_DEFAULT;
    protected int waterAmplificationRate = RATE_DEFAULT;
    protected int fireAmplificationRate = RATE_DEFAULT;
    protected int airAmplificationRate = RATE_DEFAULT;
    protected int earthAmplificationRate = RATE_DEFAULT;
    
    public Player(int maxHealth, org.bukkit.entity.Player bukkitEntity) {
        super(maxHealth, bukkitEntity);
        this.init();
    }

    public void update() {
        org.bukkit.entity.Player player = this.getBukkitEntity();
        PlayerInventory inv = player.getInventory();
        player.setHealth(this.health);
    }

    @Override
    public void init() {
        Bukkit.getScheduler().runTask(Lylac.lylacPlugin, () -> {
            update();
        });
    }

    @Override
    public void fin() {

    }

    public ArtifactInventory getArtifactInventory() { return this.artifactInventory; }

    public void showStatusAsActionBar() {
        Utils.Chat.sendActionBar(bukkitEntity, "");
    }
}
