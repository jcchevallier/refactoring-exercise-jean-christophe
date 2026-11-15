# HOKLA CODE QUALITY TEST

## Instructions

This test is a situational exercise. You are arriving on a new project with poor quality code, and your client is asking you to add a new feature by tomorrow. You are aware that you will no longer be on this project the day after tomorrow, so you must also ensure that the next developer can take over the project very easily. It's up to you to add the feature and make the code more understandable.

You are completely free in how you choose to do this; you will need to justify your choices during the debriefing.

Note: Be careful, this code is currently in production and functioning correctly according to the client's current expectations.

Good luck!

---

## Statement

Hello and welcome to the AP-HP team, the public assistance for Hoklanian practitioners.

At AP-HP, we try every day to treat our patients with the best medicines to promote their rapid recovery. That is why we only dispense medicines that we have validated ourselves.

Unfortunately, the effectiveness of basic medicines degrades as their expiration date approaches. All of them? No, because we have found a few medicines we are very proud of.

Our IT team, which you are a part of, has implemented a system to track our inventory. It was developed by Bernardos, a sensible person who has left for new adventures.

Your objective is to add a new medicine that we have just acquired so that we can quickly treat a new epidemic that is developing rapidly in France.

But first, let me introduce you to our system:

- All medicines have a `useBefore` value, which indicates the number of days remaining before their expiration dates.
- All medicines have an `efficiency` percentage, which denotes how effective the medicine is.
- At the end of each day, our system decreases these two values for each medicine (by 1 day and 1%).

Pretty simple, right?

Wait, it gets interesting:

- Once the expiration date has passed, the effectiveness degrades twice as fast.
- "Insulin vials" also decrease by 1 in effectiveness over time. However, the effectiveness decreases by 2 when there are 30 days or less remaining, and then by 3 when there are 7 days or less remaining. Finally, the effectiveness drops to 0 after the expiration date because these vials become toxic to the body.
- Our "Grandma's recipes" endure over time, so they have no expiration date and never lose effectiveness (we are even told that some recipes have extraordinary effectiveness).
- Finally, some of our pharmacists prefer rural medicine. Their "bottle of wine" increases in effectiveness as time passes and is highly appreciated by patients.

As mentioned in the preamble of our presentation, to counter various pandemics, we have recently signed a partnership with a supplier of messenger RNA vaccines. This requires an update to our system:

- "mRNA Vaccines" see their effectiveness degrade twice as fast as normal medicines, which is normal because they do not have the same history as our other medicines. In normal times, we would not dispense them, but the urgency requires us to do so.
