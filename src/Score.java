
public class Score {
	
	String date, homeTeamName, awayTeamName; // TeamName �߰�
	/*home: Ȩ����ȣ, away: ��������ȣ,
	 homeScore: Ȩ������, awayScore: ����������,
	 homeTeamName: Ȩ���̸�, awayTeamName: �������̸�,
	 no: ����ȣ, date: �������
	 */
	 
	int home,away; 
	int homeScore,awayScore;
	int no;
	
	Score(int c, String da, String homeName, String awayName, int ho, int aw, int hs, int as)
	{
		this.no= c;
		this.date= da;
		this.homeTeamName= homeName;
		this.awayTeamName= awayName;
		this.home= ho;
		this.away= aw;
		this.homeScore= hs;
		this.awayScore= as;
	}
	
	void print()
	{
		System.out.printf("%-5d %-15s %-15s %-3d:  %-5d %-15s\n",no,date,homeTeamName,homeScore,awayScore,awayTeamName);
	}
		
	void setScore(int mohoScore, int moawScore) //Score ����
	{
		this.homeScore= mohoScore;
		this.awayScore= moawScore;
	}
}
