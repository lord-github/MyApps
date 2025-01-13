unit etxt;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.Menus, Vcl.ExtCtrls, Vcl.ComCtrls,PNGimage,
System.DateUtils,  Vcl.StdCtrls, Data.DB, DBAccess, Uni, UniProvider, MySQLUniProvider, MemDS,ShellAPI,
  Vcl.FileCtrl,IdMultipartFormData, IdBaseComponent, IdComponent,
  IdTCPConnection, IdTCPClient, IdHTTP,Winsock,System.IOUtils;

type
  TForm1 = class(TForm)
    MainMenu1: TMainMenu;
    Fal1: TMenuItem;
    Sazlamalar1: TMenuItem;
    Sazlamalar2: TMenuItem;
    Ulanyjyaary1: TMenuItem;
    TreeView1: TTreeView;
    IPsalgy1: TMenuItem;
    ykmak1: TMenuItem;
    UniConnection1: TUniConnection;
    UniQuery1: TUniQuery;
    MySQLUniProvider1: TMySQLUniProvider;
    Panel2: TPanel;
    Panel3: TPanel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Panel4: TPanel;
    Button1: TButton;
    Button2: TButton;
    Button3: TButton;
    UniQuery2: TUniQuery;
    Aarygrmek1: TMenuItem;
    Button4: TButton;
    Panel5: TPanel;
    Panel1: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Panel6: TPanel;
    Label10: TLabel;
    Memo1: TMemo;
    Button5: TButton;
    Button6: TButton;
    Button7: TButton;
    Button8: TButton;
    UniQuery3: TUniQuery;
    FileListBox1: TFileListBox;
    IdHTTP1: TIdHTTP;
    UniQuery4: TUniQuery;
    UniQuery5: TUniQuery;
    procedure ykmak1Click(Sender: TObject);
    procedure Ulanyjyaary1Click(Sender: TObject);
procedure load_();
procedure getADIM(k:String);
procedure DeleteSubNodes(Node: TTreeNode);
    procedure TreeView1GetSelectedIndex(Sender: TObject; Node: TTreeNode);
    procedure FormShow(Sender: TObject);
    procedure TreeView1DblClick(Sender: TObject);
    procedure getmyID();
      procedure UploadFile(const FileName: string);
    procedure getdata(tb:String);
    procedure IPsalgy1Click(Sender: TObject);
    procedure Aarygrmek1Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button7Click(Sender: TObject);
    procedure Button5Click(Sender: TObject);
    procedure Button8Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  myIP,text,findme,s1,s2,s3:String;
goid, tekusiy, zadacaID,cislo_zadac,countzadaca,USERID,jemadim,tekadim:integer;
  IDzadan:array[0..150] of string;

implementation

{$R *.dfm}

uses klyu;
procedure TakeScreenshot(const FileName: string);
var
  ScreenDC: HDC;
  ScreenBitmap: TBitmap;
  PngImage: TPngImage;
  ScreenWidth, ScreenHeight: Integer;
begin
  // Получаем размеры экрана
  ScreenWidth := GetSystemMetrics(SM_CXSCREEN);
  ScreenHeight := GetSystemMetrics(SM_CYSCREEN);

  // Создаём объект TBitmap для хранения скриншота
  ScreenBitmap := TBitmap.Create;
  try
    ScreenBitmap.Width := ScreenWidth;
    ScreenBitmap.Height := ScreenHeight;

    // Копируем содержимое экрана в Bitmap
    ScreenDC := GetDC(0); // DC для всего экрана
    try
      BitBlt(ScreenBitmap.Canvas.Handle, 0, 0, ScreenWidth, ScreenHeight, ScreenDC, 0, 0, SRCCOPY);
    finally
      ReleaseDC(0, ScreenDC);
    end;

    // Конвертируем Bitmap в PNG
    PngImage := TPngImage.Create;
    try
      PngImage.Assign(ScreenBitmap);
      PngImage.SaveToFile(FileName); // Сохраняем PNG на диск
    finally
      PngImage.Free;
    end;

  finally
    ScreenBitmap.Free;
  end;
end;
procedure conn(Q:TUniQuery;S:String);
begin
  if q.Active then
  q.Close;
  q.SQL.Text:=s;
  q.Open;
end;

procedure addINfo(L1:TLabel;L2:TLabel;L3:TLabel;Qu:TUniQuery);
begin
conn(Qu,'SELECT COUNT(tb) as sany FROM `yumuslist` where ostalos>0');
if Qu.FieldByName('sany').AsInteger>0 then
L1.Caption:='Ulgamdaky ýumuşlar : '+Qu.FieldByName('sany').AsString else
L1.Caption:='Ulgamdaky ýumuşlar : 0 ';

conn(qu,'SELECT COUNT(tb) as sany FROM `finsh` where userid='+inttostr(USERID));
if Qu.FieldByName('sany').AsInteger>0 then
L2.Caption:='Jemi ýerine ýetirilenler : '+Qu.FieldByName('sany').AsString else
L2.Caption:='Jemi ýerine ýetirilenler : 0 ' ;

var g,a,y:integer;
g:=DayOfTheMonth(date);
a:=MonthOf(date);
y:=YearOf(date);
ShowMessage(g.ToString+'-'+a.ToString+' -'+y.ToString);
conn(qu,'SELECT COUNT(tb) as sany FROM `finsh` where userid='+inttostr(USERID)+' and '+
'a='+a.ToString+' and g='+g.ToString+' and y='+y.ToString);
if Qu.FieldByName('sany').AsInteger>0 then
L3.Caption:='Şu günki ýerine ýetirilenler : '+Qu.FieldByName('sany').AsString else
L3.Caption:='Şu günki ýerine ýetirilenler : 0 ' ;

end;

procedure TForm1.Ulanyjyaary1Click(Sender: TObject);
begin
Form2.ShowModal;
    getmyID;
    Sleep(500);
if USERID>0 then   begin
            load_;
  addINfo(Label1,Label2,Label3,UniQuery2);
end
             else
             begin
               DeleteSubNodes(TreeView1.Selected);
             ShowMessage('Açar kody ýalňyş');
             end;
end;

procedure TForm1.ykmak1Click(Sender: TObject);
begin
try
Application.Terminate;
except
end;
end;

 procedure OpenURL(const URL: string);
begin
  ShellExecute(0, 'open', PChar(URL), nil, nil, SW_SHOWNORMAL);
end;
procedure TForm1.Aarygrmek1Click(Sender: TObject);
begin
 OpenURL('http://'+myIP+'/etm/');
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
Panel5.Visible:=false;
Panel6.Visible:=true;
    tekadim:=1;
    getADIM(tekadim.ToString);
    s1:='';
    s2:='';
    s3:='';
    UniQuery2.Edit;
    UniQuery2.FieldByName('ostalos').AsInteger:=    UniQuery2.FieldByName('ostalos').AsInteger-1;
    UniQuery2.Post;
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
if tekusiy>1 then
begin
tekusiy:=tekusiy-1;
getdata(IDzadan[tekusiy]);
end;
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
if tekusiy<countzadaca then
begin
tekusiy:=tekusiy+1;
getdata(IDzadan[tekusiy]);
end;

end;

procedure TForm1.Button4Click(Sender: TObject);
begin
Panel3.Visible:=false;
TreeView1.Visible:=true;
end;

procedure TForm1.Button5Click(Sender: TObject);
begin

if tekadim>1 then
begin

  tekadim:=tekadim-1;
    getADIM(tekadim.ToString);
end;
end;


function GetLocalIP: string;
var
  WSAData: TWSAData;
  HostName: array[0..255] of AnsiChar;
  HostEnt: PHostEnt;
  Addr: PInAddr;
begin
  Result := '127.0.0.1'; // Значение по умолчанию (localhost)
  // Инициализируем Winsock
  if WSAStartup($0202, WSAData) <> 0 then
    Exit;

  try
    // Получаем имя хоста
    if gethostname(HostName, SizeOf(HostName)) = 0 then
    begin
      // Получаем информацию о хосте
      HostEnt := gethostbyname(HostName);
      if Assigned(HostEnt) then
      begin
        Addr := PInAddr(HostEnt^.h_addr_list^);
        if Assigned(Addr) then
          Result := string(inet_ntoa(Addr^)); // Преобразуем IP-адрес в строку
      end;
    end;
  finally
    WSACleanup; // Завершаем работу Winsock
  end;
end;

  procedure TForm1.UploadFile(const FileName: string);
var
  FormData: TIdMultiPartFormDataStream;

begin
                          Caption:='http://'+myIP+'/etm/upload.php';
  FormData := TIdMultiPartFormDataStream.Create;
  try
    FormData.AddFile('file', FileName, 'application/octet-stream');
    try
      IdHTTP1.Post('http://'+myIP+'/etm/upload.php', FormData);
    except
      on E: Exception do
        ShowMessage('Näsazlyk administrator bilen habarlaşyň !!!: ' + FileName + #13#10 + E.Message);
    end;
  finally
    FormData.Free;
  end;
end;

procedure TForm1.Button7Click(Sender: TObject);
var i:integer;
begin
if tekadim=4 then
begin
  if s1<>'' then
  if s2<>'' then
  if s3<>'' then
  begin
    UploadFile(s1);
    UploadFile(s2);
    UploadFile(s3);
    conn(UniQuery4,'Select * from `stepbystep`');
    UniQuery4.Insert;
    UniQuery4.FieldByName('step').AsString:='1';
        UniQuery4.FieldByName('zaid').AsString:=goid.ToString;
            UniQuery4.FieldByName('useriD').AsString:=USerID.ToString;
                UniQuery4.FieldByName('files').AsString:=TPath.GetFileName(s1);
    UniQuery4.Post;
    UniQuery4.Insert;
    UniQuery4.FieldByName('step').AsString:='2';
        UniQuery4.FieldByName('zaid').AsString:=goid.ToString;
            UniQuery4.FieldByName('useriD').AsString:=USerID.ToString;
                UniQuery4.FieldByName('files').AsString:=TPath.GetFileName(s2);
    UniQuery4.Post;
        UniQuery4.Insert;
    UniQuery4.FieldByName('step').AsString:='3';
        UniQuery4.FieldByName('zaid').AsString:=goid.ToString;
            UniQuery4.FieldByName('useriD').AsString:=USerID.ToString;
                UniQuery4.FieldByName('files').AsString:=TPath.GetFileName(s3);
    UniQuery4.Post;

       conn(UniQuery4,'Select * from `finsh`');
       UniQuery4.Insert;
       UniQuery4.FieldByName('zaid').AsInteger:=goid;
       UniQuery4.FieldByName('userid').AsInteger:=USERID;
       UniQuery4.FieldByName('otmena').AsInteger:=0;
       UniQuery4.FieldByName('tassyklanan').AsInteger:=0;
UniQuery4.FieldByName('g').AsInteger:=DayOfTheMonth(date);
UniQuery4.FieldByName('a').AsInteger:=MonthOf(date);
UniQuery4.FieldByName('y').AsInteger:=YearOf(date);
       UniQuery4.Post;
       if UniQuery4.Active then
       UniQuery4.Close;
       UniQuery4.SQL.Text:='Update `yumuslist` set `ostalos`=`ostalos`-1 where tb='+goid.ToString;
       UniQuery4.ExecSQL;
    for I := 0 to FileListBox1.Items.Count-1 do
                                                try
      DeleteFile(findme+'\'+FileListBox1.Items[i]); except
      end;

      Panel6.Visible:=false;
      Panel5.Visible:=true;
    TreeView1.Visible:=true;
     Panel3.Visible:=false;

      load_;
      addINfo(Label1,Label2,Label3,UniQuery4);
  end else
  ShowMessage('Suratlar doly däl');
end;
FileListBox1.Directory:=findme;
FileListBox1.Update;
FileListBox1.Refresh;
if tekadim<4 then
begin
  tekadim:=tekadim+1;
    getADIM(tekadim.ToString);
end;





end;


   function GenerateStringFromTemplate(const Template: string): string;
begin
  Result := StringReplace(Template, '{DATE}', DateToStr(Now), [rfReplaceAll]);
  Result := StringReplace(Result, '{RANDOM}', IntToStr(Random(1000)), [rfReplaceAll]);
end;

procedure TForm1.Button8Click(Sender: TObject);
var fn,ff:String;
sagat,min,se,gu,aa,yy:integer;
begin
Randomize;
ff:=chr(Random(25)+65)+chr(Random(25)+65)+chr(Random(25)+65);
sagat:=HourOf(time);
min:=MinuteOfTheHour(time);
se:=SecondOfTheMinute(time);
gu:=DayOfTheMonth(date);
aa:=MonthOfTheYear(date);
yy:=YearOf(date);
fn:=inttostr(tekadim)+'_'+inttostr(tekusiy)+'-'+
ff+'-'+inttostr(sagat)+inttostr(min)+inttostr(se)+inttostr(gu)+inttostr(aa)+inttostr(yy);
left:=-1900;
TakeScreenshot(findme+'\'+fn+'.png');
left:=Screen.Width div 2 - (form1.Width div 2);
if tekadim=2 then
s1:=findme+'\'+fn+'.png';
if tekadim=3 then
s2:=findme+'\'+fn+'.png';
if tekadim=4 then
s3:=findme+'\'+fn+'.png';

end;

procedure TForm1.DeleteSubNodes(Node: TTreeNode);
var
  FirstNode: TTreeNode;
begin

  FirstNode := TreeView1.Items.GetFirstNode;

  if Assigned(FirstNode) then
  begin
    FirstNode.DeleteChildren;
  end
  else

    end;
procedure TForm1.FormCreate(Sender: TObject);
begin
getdir(0,findme);
        UniConnection1.ConnectString:='Provider Name=mySQL;User ID=al;Password=1;Use Unicode=True;Data Source='+
        GetLocalIP
        +';Database=etxt;Port=3306';
        UniConnection1.Connected:=true;
        myip:=GetLocalIP;
end;

procedure TForm1.FormShow(Sender: TObject);
begin
USERID:=0;
myIP:='192.168.1.101';
    getmyID;
    Sleep(500);
if USERID>0 then  begin
             load_;
  addINfo(Label1,Label2,Label3,UniQuery2);
end
             else  begin
                            DeleteSubNodes(TreeView1.Selected);
             ShowMessage('Açar kody ýalňyş');
             end;
end;

procedure TForm1.load_();
var i:integer;
  FirstNode: TTreeNode;
key:String;
begin
key:=Trim(Form2.Edit1.Text);
DeleteSubNodes(TreeView1.Items.GetFirstNode);
conn(UniQuery1,'SELECT `tipID`,tipzadac.name_zadaca,COUNT(tipID)as sany FROM `yumuslist` '+
'INNER join tipzadac on tipzadac.tb=tipID where `yumuslist`.`tb` not in ('+
 'Select zaid from `finsh` where userid='+inttostr(USERID)
+') GROUP by name_zadaca');
if UniQuery1.RecordCount>0 then
begin
DeleteSubNodes(TreeView1.Items.GetFirstNode);
for I := 1 to UniQuery1.RecordCount do
  begin
    UniQuery1.RecNo:=i;
    FirstNode := TreeView1.Items[0];
     TreeView1.Items.AddChild(FirstNode, UniQuery1.FieldByName('name_zadaca').AsString+'('+
     UniQuery1.FieldByName('sany').AsString+')');
  end;
end;


end;

procedure TForm1.TreeView1DblClick(Sender: TObject);
var
  SelectedNode: TTreeNode;
  id:string;
  I: Integer;

begin
 SelectedNode := TreeView1.Selected;
 if Assigned(SelectedNode) then
  begin

    if Assigned(SelectedNode.Parent) then
   begin
      text:=SelectedNode.Text;
      text:=Copy(text,1,pos('(',text)-1);
      conn(UniQuery2,'Select * from `tipzadac` where `name_zadaca` ='+chr(39)+
      text
      +chr(39));
      zadacaID:=UniQuery2.FieldByName('tb').AsInteger;
    conn(UniQuery2,'Select * from  `yumuslist` where tipID='+
    zadacaid.ToString+' and ostalos>0 and tb not in '
    +'(SELECT `zaid` FROM `finsh` where `userid`='+inttostr(USERID)+')');
//    UniQuery2.SQL.Text:=InputBox('','',UniQuery2.SQL.Text);
    countzadaca:=UniQuery2.RecordCount;
    for I := 1 to UniQuery2.RecordCount do
      begin
        UniQuery2.RecNo:=i;
        IDzadan[i]:=UniQuery2.FieldByName('tb').AsString;
      end;
        tekusiy:=1;
        goid:=strtoint(IDzadan[1]);
      getdata(IDzadan[1]);
     TreeView1.Visible:=false;
     Panel3.Visible:=true;
   end;
  end;
end;

procedure TForm1.TreeView1GetSelectedIndex(Sender: TObject; Node: TTreeNode);
begin
Caption:=Node.Text;
end;


procedure TFORM1.getmyID();
begin
  if UniQuery1.Active then
UniQuery1.Close;
UniQuery1.SQL.Text:='SELECT * FROM `users` where `apikey`='+chr(39)+
TRim(Form2.Edit1.Text)
+chr(39);
UniQuery1.Open;
if UniQuery1.RecordCount>0 then
USERID:=UniQuery1.FieldByName('ustb').AsInteger else
USERID:=-1;
end;

procedure TForm1.IPsalgy1Click(Sender: TObject);
begin
   myIP:=InputBox('IPv4','',myip);
           UniConnection1.ConnectString:='Provider Name=mySQL;User ID=al;Password=1;Use Unicode=True;Data Source='+
        myIP
        +';Database=etxt;Port=3306';
        UniConnection1.Connected:=true;
end;

procedure TForm1.getdata(tb:String);
var i,komtp:integer;
kom,yumberid:string;
begin
        goid:=strtoint(tb);
   conn(UniQuery2,'Select * from `yumuslist` where tb='+tb);
   Label4.Caption:='Ýumuş barada maglumat ('+inttostr(tekusiy)+'-   '+IntToStr(countzadaca)+'-den)';
   Label6.Caption:='Ady :                    '+UniQuery2.FieldByName('nameof').AsString;
   Label5.Caption:='Görnüşi :                '+text;
   komtp:=UniQuery2.FieldByName('tipid').AsInteger;
   if ((komtp=0) or (komtp=1)or (komtp=2)or (komtp=4) )  then
   kom:='0.5 tmt tutum';
     if ((komtp=3))  then
   kom:='0.1 tmt tutum';
   yumberid:=UniQuery2.FieldByName('zakazid').AsString;

   Label8.Caption:='Töleg :                   '+UniQuery2.FieldByName('bir_baha').AsString+'('+kom+')';

   conn(UniQuery5,'Select * from users where ustb='+yumberid);
   Label7.Caption:='Ýumuş beriji :             '+UniQuery5.FieldByName('fio').AsString;



end;

procedure TForm1.getADIM(k:String);
var i:integer;
s1,ss:String;
begin
if k='1' then
conn(UniQuery3,'Select * from `yumuslist` where tb='+goid.ToString);
s1:='Hormatly ulanyjylar!'+#13#10+
'Biz size, bu ýumuşy ýerine ýetirýäniňiz üçin minnetdarlygymyzy bildirýäris.'+#13#10+
'Ýumuşy ýerine ýetirmegiňizden öň  ýumuşyň şertlerini we talaplaryny öwreniň!'+#13#10#13#10+
'Düşündirilişi : '+UniQuery3.FieldByName('dusun').AsString
;

if k='2' then
s1:='Öz akkaundyňyzy tassyklaň'+#13#10+
' Görkezilen ulgamdaky profiliňiziň suradyny birikdiriň  (Bu herekedi ýerine ýetirmek üçin profil sahypaňyza giriň we Ekranyň suradyny almak düwmesine basyň)';

if k='3' then begin
    if ((UniQuery3.FieldByName('tipID').AsInteger=3) or
 (UniQuery3.FieldByName('tipID').AsInteger=4)) then
s1:='Görkezilen sahypa geçiň!!!'+#13#10+
' Bu herekedi ýerine ýetirmek üçin ýumuş berijiniň görkezen sahypasyna giriň we ol sahypanyň suradyny ugradyň'+
#13#10#13#10+'URL : '+UniQuery3.FieldByName('url').AsString
 else
s1:='Görkezilen sahypa geçiň!!!'+#13#10+
' Bu herekedi ýerine ýetirmek üçin ýumuş berijiniň görkezen sahypasyna giriň we ol sahypanyň suradyny ugradyň'+
#13#10#13#10+'Düşündirilişi : '+UniQuery3.FieldByName('dusun').AsString;

end;
if k='4' then begin

if ((UniQuery3.FieldByName('tipID').AsInteger=3) or
 (UniQuery3.FieldByName('tipID').AsInteger=4)) then

s1:='Herekedi tassyklaň!!!'+#13#10+'Ýumuşyň şertleri : -'+
'#13#10 Ýumuşyň ýerine ýetirilendigini surada alyp ugradyň'
else
s1:='Herekedi tassyklaň!!!'+#13#13+'Ýumuşyň şertleri : '#13#10+'Teswiriň görnüşi : '+
UniQuery3.FieldByName('tesgor').AsString
+#13#10+
'Dili : '+UniQuery3.FieldByName('dili').AsString+
#13#10+
'Simwol sany : ' +UniQuery3.FieldByName('simsan').AsString+
#13#10+ 'Ýumuşyň ýerine ýetirilendigini surada alyp ugradyň'

end;
Label10.Caption:='Ädim №'+k;
Memo1.Text:=s1;
    if k<>'1' then
    Button8.Enabled:=true else
    Button8.Enabled:=false;

    if k='4' then
      Button7.Caption:='Ugratmak' else
      Button7.Caption:='Indiki ädim';

end;



end.
