package com.example.fcms;



public class TableData {
    int ID;
    String Name,Position;
    String Formation,Designation;
    String Type;
    int Age;
    String Sex;
    int Weight,Height;
    String Nation;
    double Rating;
    String Condition;
    Double Mva  ;
    int Caps  ;
    int Goals  ;
    String Current_Club  ;
    Double Transfer_FEE ;
    String Description ;
    Double Rate ;
    int Quantity ;
    Double CostperProduct ;
    int predictedsell ;
    int sold, Capacity, S_ID ;

    String Match_Type  ;
    String Stadium ;
    String Match_Area ;
    String Date  ;
    String Opponent ;
    String Result ;
    Float Match_Fee ;

    String Title ;
    String MeType ;
    int Contract_Year ;
    Float Revenue;
    int MeID ;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMeType() {
        return MeType;
    }

    public void setMeType(String meType) {
        MeType = meType;
    }

    public int getContract_Year() {
        return Contract_Year;
    }

    public void setContract_Year(int contract_Year) {
        Contract_Year = contract_Year;
    }

    public Float getRevenue() {
        return Revenue;
    }

    public void setRevenue(Float revenue) {
        Revenue = revenue;
    }

    public int getMeID() {
        return MeID;
    }

    public void setMeID(int meID) {
        MeID = meID;
    }

    public TableData(int me_id, String me_type, int me_contract_year, float me_revenue, String me_title, int anInt, String tm_match_type) {
        ID = me_id ;
        Title = me_title ;
        Type = me_type ;
        Contract_Year = me_contract_year ;
        Revenue = me_revenue ;
        MeID = anInt ;
        MeType = tm_match_type ;
    }

    public String getMatch_Type() {
        return Match_Type;
    }

    public void setMatch_Type(String match_Type) {
        Match_Type = match_Type;
    }

    public String getStadium() {
        return Stadium;
    }

    public void setStadium(String stadium) {
        Stadium = stadium;
    }

    public String getMatch_Area() {
        return Match_Area;
    }

    public void setMatch_Area(String match_Area) {
        Match_Area = match_Area;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOpponent() {
        return Opponent;
    }

    public void setOpponent(String opponent) {
        Opponent = opponent;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public Float getMatch_Fee() {
        return Match_Fee;
    }

    public void setMatch_Fee(Float match_Fee) {
        Match_Fee = match_Fee;
    }

    public String  getTeam_ID() {
        return Team_ID;
    }

    public void setTeam_ID(String team_ID) {
        Team_ID = team_ID;
    }

    String Team_ID  ;


    public TableData(int Sell_id, String Sell_name, int Sell_age, String Sell_nationality, String Sell_position, double Sell_mva, int Sell_caps, int Sell_goals, double Sell_transferfee) {
        this.ID = Sell_id ;
        Name = Sell_name;
        Age = Sell_age ;
        Nation = Sell_nationality ;
        Position = Sell_position ;
        Mva = Sell_mva ;
        Caps = Sell_caps ;
        Goals = Sell_goals ;
        Transfer_FEE = Sell_transferfee ;
    }

    public TableData(int gg_no, String gg_name, String gg_type, String gg_description, int gg_capacity, int s_id) {
        ID = gg_no ;
        Name = gg_name ;
        Type = gg_type ;
        Description = gg_description ;
        Capacity = gg_capacity;
        S_ID = s_id ;
    }

    public TableData(int tm_id, String tm_match_type, String tm_stadium, String tm_match_area, String tm_date, String tm_opponent, String tm_result, float tm_match_fee, String team_id) {
        ID = tm_id ;
        Match_Type = tm_match_type ;
        Stadium = tm_stadium ;
        Match_Area = tm_match_area ;
        Date = tm_date ;
        Opponent = tm_opponent ;
        Result = tm_result ;
        Match_Fee = tm_match_fee ;
        Team_ID = team_id ;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Double getCostperProduct() {
        return CostperProduct;
    }

    public void setCostperProduct(Double costperProduct) {
        CostperProduct = costperProduct;
    }

    public int getPredictedsell() {
        return predictedsell;
    }

    public void setPredictedsell(int predictedsell) {
        this.predictedsell = predictedsell;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getS_ID() {
        return S_ID;
    }

    public void setS_ID(int s_ID) {
        S_ID = s_ID;
    }

    public TableData(int pr_id, String pr_name, String pr_type, String pr_description, double pr_rate, int pr_quantity, double pr_proPerCost, int pr_predictedSellQuantity, int pr_sold) {
        this.ID = pr_id ;
        Name = pr_name ;
        Type = pr_type ;
        Description = pr_description ;
        Rate = pr_rate ;
        Quantity = pr_quantity ;
        CostperProduct = pr_proPerCost ;
        predictedsell = pr_predictedSellQuantity ;
        sold = pr_sold ;

    }

    public Double getMva() {
        return Mva;
    }



    public void setMva(Double mva) {
        Mva = mva;
    }

    public int getCaps() {
        return Caps;
    }

    public void setCaps(int caps) {
        Caps = caps;
    }

    public int getGoals() {
        return Goals;
    }

    public void setGoals(int goals) {
        Goals = goals;
    }

    public String getCurrent_Club() {
        return Current_Club;
    }

    public void setCurrent_Club(String current_Club) {
        Current_Club = current_Club;
    }

    public Double getTransfer_FEE() {
        return Transfer_FEE;
    }

    public void setTransfer_FEE(Double transfer_FEE) {
        Transfer_FEE = transfer_FEE;
    }

    public TableData(){

    }
    public TableData(int ID, String name, String pos, int age, String sex, int weight, int height, String nation, double rating, String condition) {
        this.ID = ID;
        Name = name;
        Position = pos;
        Age = age;
        Sex = sex;
        Weight = weight;
        Height = height;
        Nation = nation;
        Rating = rating;
        Condition = condition;
    }

    public TableData(int c_id, String c_name, String c_formation, String c_designation, int c_age, String c_sex, String c_medical_condition) {
        this.ID=c_id;
        Name=c_name;
        Formation=c_formation;
        Designation=c_designation;
        Age=c_age;
        Sex=c_sex;
        Condition=c_medical_condition;
    }

    public TableData(int s_id, String s_name, String s_type, int s_age, String s_sex, String s_medical_condition) {
        this.ID=s_id;
        Name=s_name;
        Type=s_type;
        Age=s_age;
        Sex=s_sex;
        Condition=s_medical_condition;
    }

    public TableData(int tsp_id, String tsp_name, int tsp_age, String tsp_nationality, String tsp_position, double tsp_mva, int tsp_caps, int tsp_goals, String tsp_current_club, double tsp_transferfee) {
        this.ID = tsp_id ;
        Name = tsp_name;
        Age = tsp_age ;
        Nation = tsp_nationality ;
        Position = tsp_position ;
        Mva = tsp_mva ;
        Caps = tsp_caps ;
        Goals = tsp_goals ;
        Current_Club = tsp_current_club ;
        Transfer_FEE = tsp_transferfee ;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getFormation() {
        return Formation;
    }

    public void setFormation(String formation) {
        Formation = formation;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
