# Linux Darkops

this was a project for a network programming course. We developed a complete yet concise malware suite which is composed of a downloader, malware server, and a number of different malwares such as a discord chat stealer, memory filler and a system destroyer.

## Table of Contents

- [Project Description](#project-description)
- [Installation](#installation)
- [Features](#features)
- [Repository Structure](#repository-structure)
- [Usage](#usage)
- [Disclaimer](#Disclaimer)

## Project Description

This repository contains code for a malware server and downloader, written in Java. The server allows the execution of various commands on a victim's Linux system and facilitates the download and execution of malicious files. The downloader connects to the server, retrieves malware files, and executes them on the victim's machine.


## Installation

the malwareServer.java should run on the server with a static IP, the downloader.java should be on the victim's system. the malware files should be in 'malwares' directory on the server in the same directory as the malwareServer.java 

## Features

- Supports Linux systems
- Checks if the victim's system has an antivirus installed
- Steals Discord chat data (requires Discord installation)
- Command and control functionality to manage downloaded malware
- Upload and execute custom malware files

## Repository Structure

- `malwareServer.java`: Implements the malware server functionality. It listens for connections from the downloader, communicates with it, and executes various actions.
- `downloader.java`: Represents the downloader, which connects to the malware server, retrieves malware files, and executes them on the victim's machine.
- `discord.java`: Auxiliary file for the downloader, responsible for sending Discord chat data to the server.
- `MemoryFiller.java`: Demonstrates a memory-filling malware that consumes large amounts of memory.
- `destroyer.java`: Represents a destructive malware that attempts to delete the entire file system.

## Usage

1. Start the malware server by running the `malwareServer.java` file.
2. Execute the downloader on the victim's machine by running the `downloader.java` file and providing the IP address of the malware server.
3. Follow the prompts and commands in the downloader to perform various actions on the victim's system, such as uploading and executing malware files.
4. Use the command and control functionality to manage the downloaded malware files.
5. To get a reverse shell, start a netcat listener on the server, then type 'bot' in the server prompt. This will start a netcat client on the victim's system using ncat which will connect to the netcat listener on the server providing a reverse shell!!

## Disclaimer

This code is provided for educational purposes only. Unauthorized use of this code or any malicious activities carried out with it is strictly prohibited. The authors of this code are not responsible for any misuse or damage caused by the code.

Please use this code responsibly and only in controlled environments for learning and understanding purposes.

