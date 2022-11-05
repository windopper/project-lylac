package net.kamilereon.lylac.bossbar;

import org.bukkit.boss.BossBar;

import net.kamilereon.lylac.entity.Damageable;
import net.kamilereon.lylac.entity.Player;

/**
 * 플레이어 보스바 디스플레이를 대표하는 클래스
 */
public class LylacBossBar {

    private final Player player;

    private Damageable damageableEntity = null;
    private BossBar bossBar;

    public LylacBossBar(Player player) {
        this.player = player;
    }

    /**
     * 보스바 업데이트
     */
    public void update() {
        if(this.damageableEntity != null) {
            double progress = (double) this.damageableEntity.getHealth()
             / (double) this.damageableEntity.getMaxHealth();
            bossBar.setProgress(progress);
        }
    }

    public void setEntityIfMaxHealthHigher(Damageable entity) {
        if(this.damageableEntity == null) {
            this.damageableEntity = entity;
            return;
        }
        if(this.damageableEntity.getMaxHealth() < entity.getMaxHealth()) {
            this.damageableEntity = entity;
            return;
        }
        return;
    }
}
