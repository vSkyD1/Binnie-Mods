package binnie.core.genetics;

import binnie.Binnie;
import binnie.core.BinnieCore;
import binnie.extrabees.ExtraBees;
import forestry.api.apiculture.*;
import forestry.api.genetics.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.TreeSet;

class BeeBreedingSystem extends BreedingSystem {
    public BeeBreedingSystem() {
        this.iconUndiscovered = Binnie.Resource.getItemIcon(ExtraBees.instance, "icon/undiscoveredBee");
        this.iconDiscovered = Binnie.Resource.getItemIcon(ExtraBees.instance, "icon/discoveredBee");
    }

    @Override
    public float getChance(final IMutation mutation, final EntityPlayer player, final IAllele species1, final IAllele species2) {
        return ((IBeeMutation) mutation).getChance((IBeeHousing) new VirtualBeeHousing(player), (IAlleleBeeSpecies)species1, (IAlleleBeeSpecies) species2, (IBeeGenome)this.getSpeciesRoot().templateAsGenome(this.getSpeciesRoot().getTemplate(species1.getUID())), (IBeeGenome)this.getSpeciesRoot().templateAsGenome(this.getSpeciesRoot().getTemplate(species2.getUID())));
    }

    @Override
    public ISpeciesRoot getSpeciesRoot() {
        return (ISpeciesRoot) Binnie.Genetics.getBeeRoot();
    }

    @Override
    public int getColour() {
        return 16767232;
    }

    @Override
    public Class<? extends IBreedingTracker> getTrackerClass() {
        return (Class<? extends IBreedingTracker>) IApiaristTracker.class;
    }

    @Override
    public String getAlleleName(final IChromosomeType chromosome, final IAllele allele) {
        if (chromosome == EnumBeeChromosome.FERTILITY) {
            if (allele.getUID().contains("Low")) {
                return Binnie.Language.localise(BinnieCore.instance, "allele.fertility.low");
            }
            if (allele.getUID().contains("Normal")) {
                return Binnie.Language.localise(BinnieCore.instance, "allele.fertility.normal");
            }
            if (allele.getUID().contains("High")) {
                return Binnie.Language.localise(BinnieCore.instance, "allele.fertility.high");
            }
            if (allele.getUID().contains("Maximum")) {
                return Binnie.Language.localise(BinnieCore.instance, "allele.fertility.maximum");
            }
        }
        return super.getAlleleName(chromosome, allele);
    }

    @Override
    public boolean isDNAManipulable(final ItemStack member) {
        return ((IBeeRoot) this.getSpeciesRoot()).getType(member) == EnumBeeType.LARVAE;
    }

    @Override
    public int[] getActiveTypes() {
        return new int[]{EnumBeeType.DRONE.ordinal(), EnumBeeType.PRINCESS.ordinal(), EnumBeeType.QUEEN.ordinal(), EnumBeeType.LARVAE.ordinal()};
    }

    @Override
    public void addExtraAlleles(final IChromosomeType chromosome, final TreeSet<IAllele> alleles) {
        switch ((EnumBeeChromosome) chromosome) {
            case FERTILITY: {
                for (final ForestryAllele.Fertility a : ForestryAllele.Fertility.values()) {
                    alleles.add(a.getAllele());
                }
                break;
            }
            case FLOWERING: {
                for (final ForestryAllele.Flowering a2 : ForestryAllele.Flowering.values()) {
                    alleles.add(a2.getAllele());
                }
                break;
            }
            case HUMIDITY_TOLERANCE:
            case TEMPERATURE_TOLERANCE: {
                for (final Tolerance a3 : Tolerance.values()) {
                    alleles.add(a3.getAllele());
                }
                break;
            }
            case LIFESPAN: {
                for (final ForestryAllele.Lifespan a4 : ForestryAllele.Lifespan.values()) {
                    alleles.add(a4.getAllele());
                }
                break;
            }
            case SPEED: {
                for (final ForestryAllele.Speed a5 : ForestryAllele.Speed.values()) {
                    alleles.add(a5.getAllele());
                }
                break;
            }
            case TERRITORY: {
                for (final ForestryAllele.Territory a6 : ForestryAllele.Territory.values()) {
                    alleles.add(a6.getAllele());
                }
                break;
            }
            case NOCTURNAL:
            case CAVE_DWELLING:
            case TOLERANT_FLYER: {
                for (final ForestryAllele.Bool a7 : ForestryAllele.Bool.values()) {
                    alleles.add(a7.getAllele());
                }
                break;
            }
        }
    }
}
