// �Ѿ���б� ��ǻ�Ͱ��а� 2013043360 ������
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreManager {

	ArrayList<Score> u = new ArrayList<Score>(); //����� ��ü
	Scanner s= new Scanner(System.in);
	
	String teamName[]= new String[4]; //���̸�
	int teamMatch[]= new int[4]; //���� ����
	int teamWin[]= new int[4]; //���� �¸�����
	int teamLose[]= new int[4]; //���� �й����
	int teamTie[]= new int[4]; //���� ���ºΰ���
	int teamPoint[]= new int[4]; //���� ������
	
	int count= 1;
	
	void start() //�����۾� ����
	{
		int menu,t,tnum;
		String tname;
		
		System.out.println("��⿡ ������ 4���� �Է��� �ּ���.");
		for(int i=0;i<4;i++)
		{
			System.out.printf("Team number %d. ",i);
			teamName[i]= s.nextLine();
		}
		
		while(true)
		{
			System.out.println("================================================================");
			System.out.println("1)����� ��� 2)����� ���� 3)����� ��� 4)����� �˻�");
			System.out.print("5)������ 6)������� 7)���� => ");
			menu=s.nextInt();
			System.out.println("================================================================");
			
			if(menu==7) 
			{
				System.out.println("=> ����Ǿ����ϴ�.");
				break;
			}
			else if(menu==1) addScore();
			else if(menu==2) modifyScore();
			else if(menu==3) listScores();
			else if(menu==4)
			{
				System.out.println("Team Number�� �˻��Ͻ÷��� 1��,");
				System.out.print("Team Name���� �˻��Ͻ÷��� 2���� �Է��� �ּ��� : ");
				t= s.nextInt();
				if(t==1) 
				{
					while(true)
					{
						System.out.print("Team Number : ");
						tnum= s.nextInt();
						if(tnum<0 || tnum>3) System.out.println("Team Number�� �߸��Ǿ����ϴ�. �ٽ� �Է����ּ���.");
						else 
						{
							findScores(tnum);
							break;
						}
					}
				}
				else
				{
					s.nextLine();
					while(true)
					{
						System.out.print("Team Name : ");
						tname= s.nextLine();
						tnum= -1;
						for(int i=0;i<4;i++)
						{
							if(tname.equals(teamName[i]))
							{
								tnum= i;
								break;
							}
						}
						if(tnum==-1) System.out.println("Team Name�� �߸��Ǿ����ϴ�. �ٽ� �Է����ּ���.");
						else
						{
							findScores(tname);
							break;
						}
					}					
				}
				System.out.printf("%d�� %d�� %d��, ���� %d��\n",teamWin[tnum],teamTie[tnum],teamLose[tnum],teamPoint[tnum]);
		
			}
			else if(menu==5) viewResult();
			else viewMatchScore();
			System.out.println();
		}
	}
	
	void recordMatch(int h, int a, int hs, int as, int c) //�߰� : �� ���� ������� �� ���� ���, �����Ҷ� ������ ����� �������� ���
	{
		if(hs> as)
		{
			teamWin[h]+=1*c;
			teamLose[a]+=1*c;
			teamPoint[h]+=3*c;
			
		}
		
		else if(hs< as)
		{
			teamWin[a]+=1*c;
			teamLose[h]+=1*c;
			teamPoint[a]+=3*c;
		}
		
		else
		{
			teamTie[h]+=1*c;
			teamTie[a]+=1*c;
			teamPoint[h]+=1*c;
			teamPoint[a]+=1*c;
		}
		
	}
	
	void addScore() //��������
	{
		String matchDate;
		int home,away;
		int homeScore,awayScore;
	
		s.nextLine();
		
		System.out.print("�������(Match Date) : ");
		matchDate= s.nextLine();
		
		while(true)
		{
			System.out.print("���� ��(Team Number) : ");
			home= s.nextInt();
			away= s.nextInt();
			if(home<0 || home>3 || away<0 || away>3) System.out.println("Team Number�� �߸��Ǿ����ϴ�. �ٽ� �Է����ּ���.");
			else if(home==away) System.out.println("���� �� ������ ��⸦ ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.");
			else break;
		}
		
		System.out.print("�������(MatchScore) : ");
		homeScore= s.nextInt();
		awayScore= s.nextInt();
		
		u.add(new Score(count,matchDate,teamName[home],teamName[away],home,away,homeScore,awayScore));
		
		count++;
		teamMatch[home]++;
		teamMatch[away]++;
		
		recordMatch(home,away,homeScore,awayScore,1);
		System.out.println("=> ��ϵǾ����ϴ�.");
	}
	
	void modifyScore() //���������
	{
		int n,hs,as;
		System.out.println();
		while(true)
		{
			System.out.print("������ ����ȣ�� �Է��� �ּ���. ");
			n= s.nextInt();
			if(n<0 || n>u.size()) System.out.println("�ش��ϴ� ����ȣ�� �����ϴ�. �ٽ� �Է��� �ּ���. ");
			else break;
		}
		
		recordMatch(u.get(n-1).home,u.get(n-1).away,u.get(n-1).homeScore,u.get(n-1).awayScore,-1);
		
		System.out.print("�������(MatchScore) : ");
		hs= s.nextInt();
		as= s.nextInt();
		
		u.get(n-1).setScore(hs,as);
		
		recordMatch(u.get(n-1).home,u.get(n-1).away,hs,as,1);
		System.out.println("=> �����Ǿ����ϴ�.");
		System.out.println();
		listScores();
	}
	
	void listScores() //��ϵ� ��� ��������
	{
		System.out.printf("%-5s %-15s %-15s %-11s %-15s\n","No.","Match Date","Home team","Score","Away team\n");
		for(int i=0;i<u.size();i++)
		{
			u.get(i).print();
		}
	}
	
	void findScores(int no) //������˻� - ����ȣ
	{
		System.out.println();
		System.out.printf("%-5s %-15s %-15s %-11s %-15s\n","No.","Match Date","Home team","Score","Away team\n");
		for(int i=0;i<u.size();i++)
		{
			if(u.get(i).home==no || u.get(i).away==no) u.get(i).print();
		}
	}
	
	void findScores(String name) //������˻� - ���̸�
	{
		System.out.println();
		System.out.printf("%-5s %-15s %-15s %-11s %-15s\n","No.","Match Date","Home team","Score","Away team\n");
		for(int i=0;i<u.size();i++)
		{
			if(u.get(i).homeTeamName.equals(name) || u.get(i).awayTeamName.equals(name)) u.get(i).print();
		}
	}
	
	void viewResult() //������
	{
		int max= -1,maxTeam=0;
		for(int i=0;i<4;i++)
		{
			System.out.printf("%-15s : %d�� %d�� %d��, ���� %d��\n",teamName[i],teamWin[i],teamTie[i],teamLose[i],teamPoint[i]);
			if(max< teamPoint[i])
			{
				max= teamPoint[i];
				maxTeam= i;
			}
		}
		System.out.println();
		System.out.printf("������ ���� ���� ���� %s �Դϴ�.\n",teamName[maxTeam]);
	}
	
	void viewMatchScore() //�������
	{
		int t1,t2,c=0;
		int w=0, l=0, t=0;
		int sco[]= new int[2];
	
		while(true)
		{
			System.out.println("��������� ���ϰ� ���� �� 2���� �Է��� �ּ���(Team Number) : ");
			t1= s.nextInt();
			t2= s.nextInt();
			if(t1<0 || t1>3 || t2<0 || t2>3) System.out.println("Team Number�� �߸��Ǿ����ϴ�. �ٽ� �Է����ּ���.");
			else if(t1==t2) System.out.println("���� �� ������ ��⸦ ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.");
			else break;	
		}
		//score[0]= t1���� score[1]= t2����
		
		for(int i=0;i<u.size();i++)
		{
			if(u.get(i).home== t1 && u.get(i).away== t2)
			{
				sco[0]= u.get(i).homeScore;
				sco[1]= u.get(i).awayScore;
				c=1;
			}
			if(u.get(i).home== t2 && u.get(i).away== t1)
			{
				sco[0]= u.get(i).awayScore;
				sco[1]= u.get(i).homeScore;
				c=1;
			}
			
			if(c==1) //������ ����ߴٸ�
			{
				if(sco[0]== sco[1]) t++;
				else
				{
					if(sco[0]> sco[1]) w++;
					else l++;
				}
				c=0;
			}
		}
		System.out.printf("%s�� %s ������� - %d�� %d�� %d��\n",teamName[t1],teamName[t2],w,t,l);
		System.out.printf("%s�� %s ������� - %d�� %d�� %d��\n",teamName[t2],teamName[t1],l,t,w);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreManager sm= new ScoreManager();
		sm.start();
	}
}
