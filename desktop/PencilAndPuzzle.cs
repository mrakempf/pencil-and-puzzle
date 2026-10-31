// Pencil & Puzzle desktop launcher: unpacks the bundled page and opens it in its own Edge app window.
using System;
using System.Diagnostics;
using System.IO;
using System.Reflection;
using System.Windows.Forms;

[assembly: AssemblyTitle("Pencil & Puzzle")]
[assembly: AssemblyProduct("Pencil & Puzzle")]
[assembly: AssemblyVersion("1.0.0.0")]

static class Program
{
    [STAThread]
    static void Main()
    {
        try
        {
            string home = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData), "PencilAndPuzzle");
            Directory.CreateDirectory(home);
            string page = Path.Combine(home, "index.html");
            using (Stream s = Assembly.GetExecutingAssembly().GetManifestResourceStream("index.html"))
            using (FileStream f = File.Create(page)) { s.CopyTo(f); }

            string edge = FindEdge();
            if (edge == null)
            {
                Process.Start(page);
                return;
            }
            // A dedicated profile keeps her saved progress separate and stable between launches.
            string profile = Path.Combine(home, "profile");
            string url = new Uri(page).AbsoluteUri;
            Process.Start(edge, "--app=\"" + url + "\" --user-data-dir=\"" + profile + "\" --window-size=1000,900 --no-first-run");
        }
        catch (Exception e)
        {
            MessageBox.Show("Pencil & Puzzle could not start:\n" + e.Message, "Pencil & Puzzle");
        }
    }

    static string FindEdge()
    {
        string[] roots = {
            Environment.GetFolderPath(Environment.SpecialFolder.ProgramFilesX86),
            Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles),
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData)
        };
        foreach (string r in roots)
        {
            string p = Path.Combine(r, @"Microsoft\Edge\Application\msedge.exe");
            if (File.Exists(p)) return p;
        }
        return null;
    }
}
