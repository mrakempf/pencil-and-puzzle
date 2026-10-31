# Pencil & Puzzle

An endless puzzle book in a single web page: Sudoku, Word Search, Cryptograms, Logic Grids, Fill-In, Letterboxes, Places Please, Word Chains and Word Scrambles, each at four difficulties (Easy, Medium, Hard, Expert).

- **Puzzle of the day**: a new puzzle every day, with a streak and a 7-day strip to catch up on missed days.
- **Journey**: levels in chapters of 8 around an island, getting harder as you go; up to 3 stars per level.
- **Points for hints**: every solved puzzle earns points (Easy 5, Medium 10, Hard 15, Expert 20, half again with no hints, +5 for the daily). Each hint costs 5. You start with 25.
- **XP and titles**: new puzzles earn XP toward solver levels, from "Pencil Sharpener" to "Puzzle Legend".

Progress saves in the browser on each device. When opened as a Claude artifact, it also syncs to the viewer's Claude account.

## What's in this folder

| File | What it does |
|---|---|
| `index.html` | The whole app in one file. |
| `manifest.webmanifest`, `icons/` | App name and icons used when it's added to a phone's home screen. |
| `Open Pencil & Puzzle.cmd` | Double-click to play on this PC in its own window (Microsoft Edge app mode). Works offline. |
| `Pencil & Puzzle (online).url` | Opens the published version on claude.ai. |
| `desktop/` | Source for an optional Windows launcher program (see below). |

## Put it on an iPhone

1. Host the folder with GitHub Pages: in the repository on github.com, go to **Settings → Pages**, set **Source** to **Deploy from a branch**, pick **main** and **/ (root)**, and save. After a minute the site is at `https://<username>.github.io/<repository>/`.
2. On the iPhone, open that address in **Safari**.
3. Tap **Share** (the square with an up arrow), then **Add to Home Screen**, then **Add**.

It opens full screen with its own icon. Progress is saved on the phone.

## Run it on a PC

Double-click `Open Pencil & Puzzle.cmd`, or open `index.html` in any modern browser. Nothing to install.

For a Start menu and taskbar icon, open the file in Microsoft Edge, then choose **⋯ → Apps → Install this site as an app**.

## Desktop launcher (optional)

`desktop/PencilAndPuzzle.cs` is a small Windows launcher that bundles the page and opens it in its own Edge app window. Build it with the .NET Framework compiler that ships with Windows:

```
cd desktop
C:\Windows\Microsoft.NET\Framework64\v4.0.30319\csc.exe -target:winexe -out:PencilAndPuzzle.exe -resource:..\index.html,index.html -r:System.Windows.Forms.dll PencilAndPuzzle.cs
```

Unsigned programs like this may be blocked by antivirus software.
