package npc.model;

import instances.RimPailaka;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.entity.residence.ResidenceType;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.gameserver.utils.ReflectionUtils;

/**
 * @author pchayka
 */
public final class PailakaGatekeeperInstance extends NpcInstance {

    private static final int rimIzId = 80;

    public PailakaGatekeeperInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }

        if (command.equalsIgnoreCase("rimentrance")) {
            ReflectionUtils.simpleEnterInstancedZone(player, RimPailaka.class, rimIzId);
        } else {
            super.onBypassFeedback(player, command);
        }
    }

    private boolean checkGroup(Player p) {
        if (!p.isInParty()) {
            return false;
        }
        for (Player member : p.getParty().getPartyMembers()) {
            if (member.getClan() == null) {
                return false;
            }
            if (member.getClan().getResidenceId(ResidenceType.Castle) == 0 && member.getClan().getResidenceId(ResidenceType.Fortress) == 0) {
                return false;
            }
        }
        return true;
    }
}
