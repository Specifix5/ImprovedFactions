package io.github.toberocat.improvedfactions.claims

import io.github.toberocat.improvedfactions.ImprovedFactionsPlugin
import io.github.toberocat.improvedfactions.claims.clustering.cluster.Cluster
import io.github.toberocat.improvedfactions.claims.clustering.cluster.FactionCluster
import io.github.toberocat.improvedfactions.claims.clustering.position.ChunkPosition
import io.github.toberocat.improvedfactions.claims.overclaim.ClaimSiegeManager
import io.github.toberocat.improvedfactions.factions.Faction
import io.github.toberocat.improvedfactions.user.noFactionId
import io.github.toberocat.improvedfactions.zone.Zone
import io.github.toberocat.improvedfactions.zone.ZoneHandler
import org.bukkit.Bukkit
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class FactionClaim(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FactionClaim>(FactionClaims)

    val siegeManager
        get() = ClaimSiegeManager.getManager(this)

    var world by FactionClaims.world
    var chunkX by FactionClaims.chunkX
    var chunkZ by FactionClaims.chunkZ
    var factionId by FactionClaims.factionId
    var zoneType by FactionClaims.zoneType
    var claimCluster by Cluster optionalReferencedOn FactionClaims.clusterId

    fun faction(): Faction? = Faction.findById(factionId)

    fun zone(): Zone? = ZoneHandler.getZone(zoneType)

    fun canClaim(): Boolean {
        return when {
            !canClaimInWorld(world) -> false
            factionId != noFactionId -> false
            zone()?.allowClaiming == false -> false
            else -> true
        }
    }

    fun isClaimed() = factionId != noFactionId

    fun chunk() = Bukkit.getWorld(world)?.getChunkAt(chunkX, chunkZ)
    fun toPosition() = ChunkPosition(chunkX, chunkZ, world)

    private fun getCluster() = ImprovedFactionsPlugin.instance.claimChunkClusters.getCluster(this)

    fun isRaidable() = (getCluster()?.findAdditionalType() as? FactionCluster)?.isUnprotected(chunkX, chunkZ, world)
    override fun toString(): String {
        return "FactionClaim(world='$world', chunkX=$chunkX, chunkZ=$chunkZ, factionId=$factionId, zoneType='$zoneType', claimCluster=${claimCluster?.id?.value})"
    }

}