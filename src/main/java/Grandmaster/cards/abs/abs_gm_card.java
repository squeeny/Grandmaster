package Grandmaster.cards.abs;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import Grandmaster.chr.chr_gm;
import Grandmaster.util.CardInfo;
import Grandmaster.util.TexLoader;

import static Grandmaster.grandmasterMod.makeID;

public abstract class abs_gm_card extends CustomCard {
    public static final int[] COSTS = new int[]{0, 1, 2, 3, 4 , 5};
    public static final int COST_X = -1;
    public static final int COST_UNPLAYABLE = -2;
    protected CardStrings cardStrings;
    protected String img;
    protected boolean upgradesDescription;
    protected int baseCost;
    protected boolean upgradeCost;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;
    protected boolean upgradeMokouMagic;
    protected int costUpgrade;
    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;
    protected int GMMagicUpgrade;
    protected boolean baseExhaust;
    protected boolean upgradeExhaust;
    protected boolean baseInnate;
    protected boolean upgradeInnate;
    protected boolean baseRetain;
    protected boolean upgradeRetain;
    protected boolean baseEthereal;
    protected boolean upgradeEthereal;

    public int gmSecondMagicNumber;
    public int gmBaseSecondMagicNumber;
    public boolean upgradedGMSecondMagicNumber;
    public boolean isGMSecondMagicNumberModified;

    public abs_gm_card(CardInfo cardInfo, boolean upgradesDescription) { this(chr_gm.Enums.GRANDMASTER_COLOUR, cardInfo.cardName, cardInfo.cardCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, upgradesDescription); }
    public abs_gm_card(CardColor color,
                        String cardName,
                        int cardCost,
                        CardType cardType,
                        CardTarget cardtarget,
                        CardRarity cardRarity,
                        boolean upgradesDescription) {
        super(makeID(cardName), "", (String) null, cardCost, "", cardType, color, cardRarity, cardtarget);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
        img = TexLoader.getAndLoadCardTextureString(cardName);
        this.textureImg = img;
        loadCardImage(textureImg);
        this.rarity = autoRarity();
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isGMSecondMagicNumberModified = false;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.originalName = cardStrings.NAME;
        this.name = originalName;
        this.baseCost = cost;
        this.upgradesDescription = upgradesDescription;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;
        this.upgradeMokouMagic = false;
        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;
        this.GMMagicUpgrade = 0;
        this.upgradeRetain = false;
        this.upgradeInnate = false;
        this.upgradeExhaust = false;
        this.upgradeEthereal = false;
        if (cardName.toLowerCase().contains("strike")) {
            if(this.rarity == cardRarity.BASIC){ this.tags.add(CardTags.STARTER_STRIKE); }
            this.tags.add(CardTags.STRIKE);
        } if (cardName.toLowerCase().contains("defend")) {
            if(this.rarity == cardRarity.BASIC){ this.tags.add(CardTags.STARTER_DEFEND); } }
        CommonKeywordIconsField.useIcons.set(this, true);
        InitializeCard();
    }
    /**
     * Methods to use in constructors
     */
    public void setDamage(int damage) {
        this.setDamage(damage, 0);
    }
    public void setDamage(int damage, int damageUpgrade) {
        this.baseDamage = this.damage = damage;
        if (damageUpgrade != 0) {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
        }
    }
    public void setBlock(int block) {
        this.setBlock(block, 0);
    }
    public void setBlock(int block, int blockUpgrade) {
        this.baseBlock = this.block = block;
        if (blockUpgrade != 0) {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
        }
    }
    public void setMagic(int magic) {
        this.setMagic(magic, 0);
    }
    public void setMagic(int magic, int magicUpgrade) {
        this.baseMagicNumber = this.magicNumber = magic;
        if (magicUpgrade != 0) {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
        }
    }
    public void setMokouMagic(int magic) {
        this.setMokouMagic(magic, 0);
    }
    public void setMokouMagic(int magic, int magicUpgrade) {
        this.gmBaseSecondMagicNumber = this.gmSecondMagicNumber = magic;
        if (magicUpgrade != 0) {
            this.upgradeMokouMagic = true;
            this.GMMagicUpgrade = magicUpgrade;
        }
    }
    public void setCostUpgrade(int costUpgrade) {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }
    public void setExhaust(boolean exhaust) {
        this.setExhaust(exhaust, exhaust);
    }
    public void setExhaust(boolean baseExhaust, boolean upgExhaust) {
        this.baseExhaust = baseExhaust;
        this.upgradeExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }
    public void setInnate(boolean innate) {
        this.setInnate(innate, innate);
    }
    public void setInnate(boolean baseInnate, boolean upgInnate) {
        this.baseInnate = baseInnate;
        this.isInnate = baseInnate;
        this.upgradeInnate = upgInnate;
    }
    public void setRetain(boolean retain) {
        this.setRetain(retain, retain);
    }
    public void setRetain(boolean baseRetain, boolean upgRetain) {
        this.baseRetain = baseRetain;
        this.selfRetain = baseRetain;
        this.upgradeRetain = upgRetain;
    }
    public void setEthereal(boolean ethereal) {
        this.setEthereal(ethereal, ethereal);
    }
    public void setEthereal(boolean baseEthereal, boolean upgEthereal) {
        this.baseEthereal = baseEthereal;
        this.isEthereal = baseEthereal;
        this.upgradeEthereal = upgEthereal;
    }
    public void setMultiDamage(boolean isMultiDamage) {
        this.isMultiDamage = isMultiDamage;
    }
    private CardRarity autoRarity() {
        String packageName = this.getClass().getPackage().getName();
        String directParent;
        if (packageName.contains(".")) { directParent = packageName.substring(1 + packageName.lastIndexOf("."));
        } else { directParent = packageName; }
        switch (directParent) {
            case "com": return CardRarity.COMMON;
            case "unc": return CardRarity.UNCOMMON;
            case "rar": return CardRarity.RARE;
            case "bas": return CardRarity.BASIC;
            default: return CardRarity.SPECIAL;
        }
    }
    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        if (card instanceof abs_gm_card) {
            card.rawDescription = this.rawDescription;
            ((abs_gm_card) card).upgradesDescription = this.upgradesDescription;
            ((abs_gm_card) card).baseCost = this.baseCost;
            ((abs_gm_card) card).upgradeCost = this.upgradeCost;
            ((abs_gm_card) card).upgradeDamage = this.upgradeDamage;
            ((abs_gm_card) card).upgradeBlock = this.upgradeBlock;
            ((abs_gm_card) card).upgradeMagic = this.upgradeMagic;
            ((abs_gm_card) card).upgradeMokouMagic = this.upgradeMokouMagic;
            ((abs_gm_card) card).costUpgrade = this.costUpgrade;
            ((abs_gm_card) card).damageUpgrade = this.damageUpgrade;
            ((abs_gm_card) card).blockUpgrade = this.blockUpgrade;
            ((abs_gm_card) card).magicUpgrade = this.magicUpgrade;
            ((abs_gm_card) card).GMMagicUpgrade = this.GMMagicUpgrade;
            ((abs_gm_card) card).baseExhaust = this.baseExhaust;
            ((abs_gm_card) card).upgradeExhaust = this.upgradeExhaust;
            ((abs_gm_card) card).baseInnate = this.baseInnate;
            ((abs_gm_card) card).upgradeInnate = this.upgradeInnate;
            ((abs_gm_card) card).baseRetain = this.baseRetain;
            ((abs_gm_card) card).upgradeRetain = this.upgradeRetain;
            ((abs_gm_card) card).baseEthereal = this.baseEthereal;
            ((abs_gm_card) card).upgradeEthereal = this.upgradeEthereal;
            ((abs_gm_card) card).gmBaseSecondMagicNumber = this.gmBaseSecondMagicNumber;
            ((abs_gm_card) card).gmSecondMagicNumber = this.gmSecondMagicNumber;
        }
        return card;
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            if (this.upgradesDescription) { this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
            if (upgradeCost) {
                int diff = this.baseCost - this.cost; //positive if cost is reduced
                this.upgradeBaseCost(costUpgrade);
                this.cost -= diff;
                this.costForTurn -= diff;
                if (cost < 0) { cost = 0; }
                if (costForTurn < 0) { costForTurn = 0; }
            }
            if (upgradeDamage) { this.upgradeDamage(damageUpgrade); }
            if (upgradeBlock) { this.upgradeBlock(blockUpgrade); }
            if (upgradeMagic) { this.upgradeMagicNumber(magicUpgrade); }
            if (upgradeMokouMagic) { this.upgradeGMSecondMagicNumber(GMMagicUpgrade); }
            if (baseExhaust ^ upgradeExhaust) { this.exhaust = upgradeExhaust; }
            if (baseInnate ^ upgradeInnate) { this.isInnate = upgradeInnate; }
            if (baseRetain ^ upgradeRetain) { this.selfRetain = upgradeRetain; }
            if (baseEthereal ^ upgradeEthereal) { this.isEthereal = upgradeEthereal; }
            this.initializeDescription();
        }
    }
    public void InitializeCard() {
        FontHelper.cardDescFont_N.getData().setScale(1.0f);
        this.initializeTitle();
        this.initializeDescription();
    }
    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedGMSecondMagicNumber) {
            gmSecondMagicNumber = gmBaseSecondMagicNumber;
            isGMSecondMagicNumberModified = true;
        }
    }
    public void upgradeGMSecondMagicNumber(int amount) {
        gmBaseSecondMagicNumber += amount;
        gmSecondMagicNumber = gmBaseSecondMagicNumber;
        upgradedGMSecondMagicNumber = true;
    }
}