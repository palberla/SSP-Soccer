package de.hofuniversity.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import javax.persistence.*;

import de.hofuniversity.core.connect.ConnectToPlayer;
import de.hofuniversity.io.xml.util.PlayerNameEqualizer;
import de.hofuniversity.util.ExceptionText;

/**
 * 
 */

/**
 * @author Michael Jahn
 */
@Entity
@Table(name = "t_team")
public class Team extends Named implements ConnectToPlayer {
    @Transient
    private PlayerNameEqualizer	playerNameEqualizer;

    @Column(name = "c_icon_url")
    private String		iconURL;
    
    @OneToMany(mappedBy="team", fetch=FetchType.LAZY)
    private Collection<Player>	playerCollection;

  @Transient
//    @ElementCollection(targetClass=Match.class)
    private Collection<Match>	matchCollection;

    @ManyToOne(targetEntity = Stadium.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "c_stadium_id", referencedColumnName = "c_id")
    private Stadium stadium;
    @Transient
    private Player cachedPlayer;

    public Team() {
    }

    public String getIconURL() {
	return this.iconURL;
    }

    public void setIconURL(String iconURL) {
	if (iconURL == null) {
	    throw new IllegalArgumentException(ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("icon url", this));
	}
	this.iconURL = iconURL;
    }

    private Collection<Player> getPlayerCollection() {
	if (playerCollection == null) {
	    playerCollection = new HashSet<Player>();
	}
	return this.playerCollection;
    }
    
    public Collection<Player> getUnmodifiablePlayerCollection() {
	Collection<Player> playerCollection = this.getPlayerCollection();
	if (playerCollection.isEmpty()) {
	    return Collections.emptySet();
	}
	return Collections.unmodifiableCollection(playerCollection);
    }

    public void addPlayer(Player player) {
	if (player == null) {
	    throw new IllegalArgumentException(ExceptionText.getInstance().getAddNullIllegalArgumentExceptionMessage("player", this));
	}
	this.getPlayerCollection().add(player);
    }

    private Collection<Match> getMatchCollection() {
	if (this.matchCollection == null) {
	    this.matchCollection = new ArrayList<Match>();
	}
	return this.matchCollection;
    }

    public Collection<Match> getUnmodifiableMatchCollection() {
	Collection<Match> matchCollection = this.getMatchCollection();
	if (matchCollection.isEmpty()) {
	    return Collections.emptySortedSet();
	}
	return Collections.unmodifiableCollection(matchCollection);
    }

    public void addMatch(Match match) {
	if (match == null) {
	    throw new IllegalArgumentException(ExceptionText.getInstance().getAddNullIllegalArgumentExceptionMessage("match", this));
	}
	this.getMatchCollection().add(match);
    }

    public boolean containsPlayer(String name) {
	return this.getThisPlayer(name) != null;
    }

    private Player getThisPlayer(String name) {

	if (playerNameEqualizer == null) {
	    playerNameEqualizer = new PlayerNameEqualizer();
	}
	if (name == null) {
	    throw new IllegalArgumentException("Cannot get player with NULL name out of team " + this.getName() + ".");
	}
	name = name.trim();
	if (name.isEmpty()) {
	    throw new IllegalArgumentException("Cannot get player with empty name out of team " + this.getName() + ".");
	}
	for (Player player : this.getPlayerCollection()) {
	    if (playerNameEqualizer.equalsName(player.getName(), name)) {
		this.cachedPlayer = player;
		return this.cachedPlayer;
	    }
	}
	return null;
    }

    public Player getPlayer(String name) {
	if (this.cachedPlayer != null && playerNameEqualizer.equalsName(this.cachedPlayer.getName(), name)) {
	    return this.cachedPlayer;
	}
	Player player = this.getThisPlayer(name);
	if (player == null) {
	    throw new IllegalArgumentException("Player with name " + name + "does not exist in team" + this.getName());
	}
	return player;
    }

    public Stadium getStadium() {
	return this.stadium;
    }

    public void setStadium(Stadium stadium) {
	if (stadium == null) {
	    throw new IllegalArgumentException(ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("stadium", this));
	}
	this.stadium = stadium;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	// String playerString =
	// Arrays.toString(this.getPlayerCollection().toArray());
	// return "Der Verein " + this.getName() //+ " hat folgende Spieler: " +
	// playerString.substring(1, playerString.length() - 1);
	return "Verein: " + this.getName() + "[" + this.getId() + "] mit dem Stadion "
		+ ((this.getStadium() != null) ? this.getStadium() : null) + " und dem Vereinswappen: " + this.getIconURL();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hofuniversity.core.ConnectToPlayer#conntectToPlayer(de.hofuniversity.
     * core.Player)
     */
    @Override
    public void conntectToPlayer(Player player) {
	if (player == null) {
	    throw new IllegalArgumentException(ExceptionText.getInstance().getConnectNullIllegalArgumentExceptionMessage("player", this));
	}
	this.addPlayer(player);
	player.setTeam(this);
    }
}
