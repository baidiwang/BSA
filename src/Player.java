public class Player {
    private String id;
    private String name;
    private Double offense;
    private Double defense;
    private Double total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOffense() {
        return offense;
    }

    public void setOffense(Double offense) {
        this.offense = offense;
    }

    public Double getDefense() {
        return defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String toString() {
        return "Player ID: " + id + ", Player Name: " + name + ", Offense: " + offense + ", Defense" + defense + ", Total: " + total + "\n";
    }
}
