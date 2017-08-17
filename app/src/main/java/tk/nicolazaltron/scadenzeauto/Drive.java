package tk.nicolazaltron.scadenzeauto;

import android.content.Intent;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;


/*public class Drive {

    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data){

    private void main() throws IOException {
            final String title = "configuration file";
            File config;

            ByteArrayContent content = new ByteArrayContent("text/plain", "Some configuration, or sone history, or blablabla.");

            List<ParentReference> parents = new ArrayList<ParentReference>();
            parents.add(new ParentReference().setId("appdata"));

            config = new File().setTitle(title).setParents(parents);
            config = service.files().insert(config.content).execute();
            showToast(config.getId() + "created");
            Drive.Files.List request = service.files().list();
            request.setQ("'appdata' in parents AND title = '" + title + "'");
            List<File> files = request.execute().getItems();
            if (files.size() > 0){
                config = files.get(0);
                showToast("found");
            }
            else{
            showToast("notFound");
            }
            content = new ByteArrayContent("text json".getBytes());
            config = service.files().update(config.getId(), config, content).execute();
            showToast("updated");
    }

    private void run(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    main();
                } catch (UserRecoverableAuthIOException e){
                    e.printStackTrace();
                    startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private Drive getDriveService(GoogleAccountCredential credential){
        return new Drive.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), credential).build();
    }

    public void showToast(final String toast){
        runOnUThread(new Runnable(){
            @Override
            public void run(){
                Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
*/
