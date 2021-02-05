package model.Utils;

import javax.swing.*;

// NOTE: Due to getClass().getResource(string) behaviour, only searches within the same package
public class ImageFactory {
    private ImageIcon cavalryPiece = new ImageIcon(getClass().getResource("Images/Cavalry.jpg"));
    private ImageIcon archerPiece = new ImageIcon(getClass().getResource("Images/Archer.jpg"));
    private ImageIcon spearmanPiece = new ImageIcon(getClass().getResource("Images/Spearman.jpg"));
    private ImageIcon ninjaPiece = new ImageIcon(getClass().getResource("Images/Ninja.jpg"));
    private ImageIcon warriorPiece = new ImageIcon(getClass().getResource("Images/Warrior.jpg"));
    private ImageIcon warlordPiece = new ImageIcon(getClass().getResource("Images/Warlord.jpg"));
    private ImageIcon flag = new ImageIcon (getClass().getResource("Images/Flag.png"));
    
    //flagged icon pieces
    private ImageIcon flaggedCavalryPiece = new ImageIcon(getClass().getResource("Images/CavalryFlagged.jpg"));
    private ImageIcon flaggedArcherPiece = new ImageIcon(getClass().getResource("Images/ArcherFlagged.jpg"));
    private ImageIcon flaggedSpearmanPiece = new ImageIcon(getClass().getResource("Images/SpearmanFlagged.jpg"));
    private ImageIcon flaggedNinjaPiece = new ImageIcon(getClass().getResource("Images/NinjaFlagged.jpg"));
    private ImageIcon flaggedWarriorPiece = new ImageIcon(getClass().getResource("Images/WarriorFlagged.jpg"));
    private ImageIcon flaggedWarlordPiece = new ImageIcon(getClass().getResource("Images/WarlordFlagged.jpg"));
    private ImageIcon flagSamurai = new ImageIcon(getClass().getResource("Images/FlagSamurai.png"));
    private ImageIcon flagKnight = new ImageIcon(getClass().getResource("Images/FlagKnight.png"));

    public ImageIcon getCavalryImage() {
        return cavalryPiece;
    }

    public ImageIcon getArcherImage() {
        return archerPiece;
    }

    public ImageIcon getSpearmanImage() {
        return spearmanPiece;
    }

    public ImageIcon getNinjaImage() {
        return ninjaPiece;
    }

    public ImageIcon getWarriorImage() {
        return warriorPiece;
    }

    public ImageIcon getWarlordImage() {
        return warlordPiece;
    }

    public ImageIcon getFlagImage() {
        return flag;
    }
    
    public ImageIcon getFlaggedCavalryImage() {
        return flaggedCavalryPiece;
    }
    
    public ImageIcon getFlaggedArcherImage() {
        return flaggedArcherPiece;
    }
    
    public ImageIcon getFlaggedSpearmanImage() {
        return flaggedSpearmanPiece;
    }
    
    public ImageIcon getFlaggedNinjaImage() {
        return flaggedNinjaPiece;
    }
    
    public ImageIcon getFlaggedWarriorImage() {
        return flaggedWarriorPiece;
    }
    
    public ImageIcon getFlaggedWarlordImage() {
        return flaggedWarlordPiece;
    }
    
    public ImageIcon getSamuraiFlagImage() {
        return flagSamurai;
    }
    
    public ImageIcon getKnightFlagImage() {
        return flagKnight;
    }


}
